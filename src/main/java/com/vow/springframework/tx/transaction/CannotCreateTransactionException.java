package com.vow.springframework.tx.transaction;

/**
 * @author: wushaopeng
 * @date: 2023/1/13 9:55
 */
public class CannotCreateTransactionException extends TransactionException{

    public CannotCreateTransactionException(String message) {
        super(message);
    }

    public CannotCreateTransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
