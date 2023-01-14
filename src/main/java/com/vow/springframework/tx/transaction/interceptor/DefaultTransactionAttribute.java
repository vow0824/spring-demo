package com.vow.springframework.tx.transaction.interceptor;

import com.vow.springframework.tx.transaction.support.DefaultTransactionDefinition;

/**
 * Spring's common transaction attribute implementation.
 * Rolls back on runtime, but not checked, exceptions by default.
 * @author: wushaopeng
 * @date: 2023/1/13 14:40
 */
public class DefaultTransactionAttribute extends DefaultTransactionDefinition implements TransactionAttribute {

    public DefaultTransactionAttribute() {
        super();
    }

    @Override
    public boolean rollbackOn(Throwable e) {
        return (e instanceof RuntimeException || e instanceof Error);
    }

    @Override
    public String toString() {
        return "DefaultTransactionAttribute{}";
    }
}
