package com.vow.springframework.tx.transaction.support;

import com.vow.springframework.tx.transaction.NestedTransactionNotSupportedException;
import com.vow.springframework.tx.transaction.SavepointManager;
import com.vow.springframework.tx.transaction.TransactionException;
import com.vow.springframework.tx.transaction.TransactionStatus;

import java.io.IOException;

/**
 * @author: wushaopeng
 * @date: 2023/1/13 9:49
 */
public abstract class AbstractTransactionStatus implements TransactionStatus {

    private boolean rollbackOnly = false;

    private boolean completed = false;

    private Object savepoint;

    @Override
    public void setRollbackOnly() {
        this.rollbackOnly = true;
    }

    @Override
    public boolean isRollbackOnly() {
        return (isLocalRollbackOnly() || isGlobalRollbackOnly());
    }

    public boolean isLocalRollbackOnly() {
        return this.rollbackOnly;
    }

    public boolean isGlobalRollbackOnly() {
        return false;
    }

    @Override
    public void flush() throws IOException {

    }

    public void setCompleted() {
        this.completed = true;
    }

    @Override
    public boolean isCompleted() {
        return completed;
    }

    public Object getSavepoint() {
        return savepoint;
    }

    public void setSavepoint(Object savepoint) {
        this.savepoint = savepoint;
    }

    @Override
    public boolean hasSavepoint() {
        return this.savepoint != null;
    }

    @Override
    public Object createSavepoint() throws TransactionException {
        return getSavepointManager().createSavepoint();
    }

    @Override
    public void rollbackToSavepoint(Object savepoint) throws TransactionException {
        getSavepointManager().rollbackToSavepoint(savepoint);
    }

    @Override
    public void releaseSavepoint(Object savepoint) throws TransactionException {
        getSavepointManager().releaseSavepoint(savepoint);
    }

    protected SavepointManager getSavepointManager() {
        throw new NestedTransactionNotSupportedException("This transaction dose not support savepoint");
    }
}
