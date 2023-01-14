package com.vow.springframework.tx.transaction.interceptor;

import com.vow.springframework.tx.transaction.TransactionDefinition;

/**
 * This interface adds a {@code rollbackOn} specification to {@link TransactionDefinition}.
 * @author: wushaopeng
 * @date: 2023/1/13 13:29
 */
public interface TransactionAttribute extends TransactionDefinition {

    boolean rollbackOn(Throwable e);
}
