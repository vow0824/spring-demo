package com.vow.springframework.bean;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author: wushaopeng
 * @date: 2022/11/30 11:51
 */
public class UserServiceInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return methodInvocation.proceed();
        } finally {
            System.out.println("monitor - Begin by aop");
            System.out.println("method name:" + methodInvocation.getMethod());
            System.out.println("time consuming:" + (System.currentTimeMillis() - start) + "ms");
            System.out.println("monitor - End");
        }
    }
}
