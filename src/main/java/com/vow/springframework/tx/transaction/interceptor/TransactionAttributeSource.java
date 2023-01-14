package com.vow.springframework.tx.transaction.interceptor;

import java.lang.reflect.Method;

/**
 * Strategy interface used by {@link TransactionInterceptor} for metadata retrieval.
 * @author: wushaopeng
 * @date: 2023/1/13 15:12
 */
public interface TransactionAttributeSource {

    TransactionAttribute getTransactionAttribute(Method method, Class<?> targetClass);
}
