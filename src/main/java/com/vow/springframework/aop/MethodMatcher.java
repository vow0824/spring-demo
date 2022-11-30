package com.vow.springframework.aop;

import java.lang.reflect.Method;

/**
 * Part of a {@link Pointcut}: Checks whether the target method is eligible for advice.
 *
 * @author: wushaopeng
 * @date: 2022/11/30 10:25
 */
public interface MethodMatcher {

    /**
     * Perform static checking whether the given method matches. If this
     * @return whether this method matches statically
     */
    boolean matches(Method method, Class<?> targetClass);
}
