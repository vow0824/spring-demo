package com.vow.springframework.tx.transaction.interceptor;

import com.vow.springframework.tx.transaction.TransactionDefinition;
import com.vow.springframework.tx.transaction.support.DelegatingTransactionDefinition;

import java.io.Serializable;

/**
 * @author: wushaopeng
 * @date: 2023/1/13 15:02
 */
public class DelegatingTransactionAttribute extends DelegatingTransactionDefinition implements TransactionAttribute, Serializable {

    private final TransactionAttribute targetAttribute;

    public DelegatingTransactionAttribute(TransactionAttribute targetAttribute) {
        super(targetAttribute);
        this.targetAttribute = targetAttribute;
    }

    @Override
    public boolean rollbackOn(Throwable e) {
        return this.targetAttribute.rollbackOn(e);
    }
}
