package com.vow.springframework.tx.transaction.support;

import com.vow.springframework.tx.transaction.TransactionDefinition;

import java.io.Serializable;

/**
 * @author: wushaopeng
 * @date: 2023/1/13 10:11
 */
public class DefaultTransactionDefinition implements TransactionDefinition, Serializable {

    private int propagationBehavior = PROPAGATION_REQUIRED;

    private int isolationLevel = ISOLATION_DEFAULT;

    private int timeout = TIMEOUT_DEFAULT;

    private boolean readOnly = false;

    private String name;

    public DefaultTransactionDefinition() {
    }

    public DefaultTransactionDefinition(int propagationBehavior, int isolationLevel, int timeout, boolean readOnly, String name) {
        this.propagationBehavior = propagationBehavior;
        this.isolationLevel = isolationLevel;
        this.timeout = timeout;
        this.readOnly = readOnly;
        this.name = name;
    }

    public DefaultTransactionDefinition(int propagationBehavior) {
        this.propagationBehavior = propagationBehavior;
    }

    public void setPropagationBehavior(int propagationBehavior) {
        this.propagationBehavior = propagationBehavior;
    }

    @Override
    public int getPropagationBehavior() {
        return this.propagationBehavior;
    }

    @Override
    public int getIsolationLevel() {
        return this.isolationLevel;
    }

    public void setTimeout(int timeout) {
        if (timeout < TIMEOUT_DEFAULT) {
            throw new IllegalArgumentException("Timeout must be a positive integer or TIMEOUT_DEFAULT");
        }
        this.timeout = timeout;
    }

    @Override
    public int getTimeout() {
        return 0;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    @Override
    public boolean isReadOnly() {
        return false;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return null;
    }
}
