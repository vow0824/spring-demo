package com.vow.springframework.aop;

/**
 * Superinterface for all Advisors that are driven by a pointcut.
 * This covers nearly all advisors except introduction advisors,
 * for which method-level matching doesn't apply.
 *
 * @author: wushaopeng
 * @date: 2022/12/1 9:19
 */
public interface PointcutAdvisor extends Advisor {

    /**
     * Get the Pointcut that drives this advisor.
     */
    Pointcut getPointcut();
}