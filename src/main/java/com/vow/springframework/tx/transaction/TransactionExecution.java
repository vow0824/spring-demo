package com.vow.springframework.tx.transaction;

/**
 * @author: wushaopeng
 * @date: 2023/1/13 9:18
 */
public interface TransactionExecution {

    /**
     * Return whether the present transaction is new; otherwise participating
     * in an existing transaction, or potentially not running in an actual
     * transaction in the first place.
     */
    boolean isNewTransaction();

    /**
     * Set the transaction rollback-only. This instructs the transaction manager
     * that the only possible outcome of the transaction may be a rollback, as
     * alternative to throwing an exception which would in turn trigger a rollback.
     */
    void setRollbackOnly();

    /**
     * Return whether the transaction has been marked as rollback-only
     * (either by the application or by the transaction infrastructure).
     */
    boolean isRollbackOnly();

    /**
     * Return whether this transaction is completed, that is,
     * whether it has already been committed or rolled back.
     */
    boolean isCompleted();
}
