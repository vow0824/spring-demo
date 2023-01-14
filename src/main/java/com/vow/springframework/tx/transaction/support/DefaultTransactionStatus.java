package com.vow.springframework.tx.transaction.support;

/**
 * Default implementation of the TransactionStatus
 * interface, used by {@link AbstractPlatformTransactionManager}. Based on the concept of an underlying "transaction object".
 * @author: wushaopeng
 * @date: 2023/1/13 10:01
 */
public class DefaultTransactionStatus extends AbstractTransactionStatus{

    private final Object transaction;

    private final boolean newTransaction;

    public DefaultTransactionStatus(Object transaction, boolean newTransaction) {
        this.transaction = transaction;
        this.newTransaction = newTransaction;
    }

    public Object getTransaction() {
        return transaction;
    }

    public boolean hasTransaction() {
        return this.transaction != null;
    }

    @Override
    public boolean isNewTransaction() {
        return hasTransaction() && this.newTransaction;
    }
}
