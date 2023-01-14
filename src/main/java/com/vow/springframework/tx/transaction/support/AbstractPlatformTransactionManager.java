package com.vow.springframework.tx.transaction.support;

import com.vow.springframework.tx.transaction.PlatformTransactionManager;
import com.vow.springframework.tx.transaction.TransactionDefinition;
import com.vow.springframework.tx.transaction.TransactionException;
import com.vow.springframework.tx.transaction.TransactionStatus;

import java.io.Serializable;

/**
 * @author: wushaopeng
 * @date: 2023/1/13 9:45
 */
public abstract class AbstractPlatformTransactionManager implements PlatformTransactionManager, Serializable {

    @Override
    public final TransactionStatus getTransactionStatus(TransactionDefinition definition) throws TransactionException {
        Object transaction = doGetTransaction();
        if (null == definition) {
            definition = new DefaultTransactionDefinition();
        }
        if (definition.getTimeout() < TransactionDefinition.TIMEOUT_DEFAULT) {
            throw new TransactionException("Invalid transaction timeout " + definition.getTimeout());
        }
        // 暂定事务传播为默认的行为
        DefaultTransactionStatus status = newTransactionStatus(definition, transaction, true);
        // 开始事务
        doBegin(transaction, definition);
        return status;
    }

    protected DefaultTransactionStatus newTransactionStatus(TransactionDefinition definition, Object transaction, boolean newTransaction) {
        return new DefaultTransactionStatus(transaction, newTransaction);
    }

    @Override
    public void commit(TransactionStatus status) throws TransactionException {
        if (status.isCompleted()) {
            throw new IllegalArgumentException("Transaction is already completed - do not call or rollback more than once per transaction");
        }
        DefaultTransactionStatus defStatus = (DefaultTransactionStatus) status;
        processCommit(defStatus);
    }

    private void processCommit(DefaultTransactionStatus status) throws TransactionException {
        doCommit(status);
    }

    @Override
    public void rollback(TransactionStatus status) throws TransactionException {
        if (status.isCompleted()) {
            throw new IllegalArgumentException("Transaction is already completed - do not call or rollback more than once per transaction");
        }
        DefaultTransactionStatus defStatus = (DefaultTransactionStatus) status;
        processRollback(defStatus, false);
    }

    private void processRollback(DefaultTransactionStatus status, boolean unexpected) throws TransactionException {
        doRollback(status);
    }

    protected abstract Object doGetTransaction() throws TransactionException;

    protected abstract void doCommit(DefaultTransactionStatus status) throws TransactionException;

    protected abstract void doRollback(DefaultTransactionStatus status) throws TransactionException;

    protected abstract void doBegin(Object transaction, TransactionDefinition definition) throws TransactionException;
}
