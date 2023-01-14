package com.vow.springframework.tx.transaction.interceptor;

import com.vow.springframework.tx.transaction.PlatformTransactionManager;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.io.Serializable;

/**
 * AOP Alliance MethodInterceptor for declarative transaction
 * @author: wushaopeng
 * @date: 2023/1/13 15:14
 */
public class TransactionInterceptor extends TransactionAspectSupport implements MethodInterceptor, Serializable {

    public TransactionInterceptor(PlatformTransactionManager platformTransactionManager, TransactionAttributeSource transactionAttributeSource) {
        setTransactionManager(platformTransactionManager);
        setTransactionAttributeSource(transactionAttributeSource);
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        return invokeWithinTransaction(methodInvocation.getMethod(), methodInvocation.getThis().getClass(), methodInvocation::proceed);
    }
}
