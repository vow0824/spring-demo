package com.vow.springframework.tx.transaction;

/**
 * @author: wushaopeng
 * @date: 2023/1/13 9:27
 */
public class TransactionException extends RuntimeException {

    public TransactionException(String message) {
        super(message);
    }

    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
