package com.vow.springframework.bean;

import com.vow.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author: wushaopeng
 * @date: 2022/12/1 10:12
 */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("intercept method:" + method.getName());
    }
}
