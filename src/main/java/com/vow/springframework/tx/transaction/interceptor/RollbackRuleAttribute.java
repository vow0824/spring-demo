package com.vow.springframework.tx.transaction.interceptor;

import java.io.Serializable;

/**
 * @author: wushaopeng
 * @date: 2023/1/13 14:37
 */
public class RollbackRuleAttribute implements Serializable {

    private final String exceptionName;

    public RollbackRuleAttribute(Class<?> clazz) {
        this.exceptionName = clazz.getName();
    }
}
