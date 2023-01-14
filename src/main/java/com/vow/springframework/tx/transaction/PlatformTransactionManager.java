package com.vow.springframework.tx.transaction;

/**
 * @author: wushaopeng
 * @date: 2023/1/13 9:17
 */
public interface PlatformTransactionManager {

    TransactionStatus getTransactionStatus(TransactionDefinition definition) throws TransactionException;

    void commit(TransactionStatus status) throws TransactionException;

    void rollback(TransactionStatus status) throws TransactionException;
}
