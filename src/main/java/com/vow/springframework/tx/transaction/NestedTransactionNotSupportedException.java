package com.vow.springframework.tx.transaction;

/**
 * @author: wushaopeng
 * @date: 2023/1/13 9:56
 */
public class NestedTransactionNotSupportedException extends CannotCreateTransactionException{

    public NestedTransactionNotSupportedException(String message) {
        super(message);
    }

    public NestedTransactionNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }
}
