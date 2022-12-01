package com.vow.springframework.aop.aspectj;

import com.vow.springframework.aop.Pointcut;
import com.vow.springframework.aop.PointcutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @author: wushaopeng
 * @date: 2022/12/1 9:32
 */
public class AspectJExpressionPointcutAdvisor implements PointcutAdvisor {

    // 切面
    private AspectJExpressionPointcut pointcut;

    // 具体的拦截方法
    private Advice advice;

    // 表达式
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public Advice getAdvice() {
        return advice;
    }

    @Override
    public Pointcut getPointcut() {
        if (null == pointcut) {
            return new AspectJExpressionPointcut(expression);
        }
        return pointcut;
    }

    public void setAdvice(Advice advice) {
        this.advice = advice;
    }
}
