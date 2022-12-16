package com.vow.springframework;

import com.vow.springframework.aop.AdvisedSupport;
import com.vow.springframework.aop.ClassFilter;
import com.vow.springframework.aop.MethodMatcher;
import com.vow.springframework.aop.TargetSource;
import com.vow.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.vow.springframework.aop.aspectj.AspectJExpressionPointcutAdvisor;
import com.vow.springframework.aop.framework.Cglib2AopProxy;
import com.vow.springframework.aop.framework.JdkDynamicAopProxy;
import com.vow.springframework.aop.framework.ProxyFactory;
import com.vow.springframework.aop.framework.ReflectiveMethodInvocation;
import com.vow.springframework.aop.framework.adaper.MethodBeforeAdviceInterceptor;
import com.vow.springframework.bean.IUserService;
import com.vow.springframework.bean.UserService;
import com.vow.springframework.bean.UserServiceBeforeAdvice;
import com.vow.springframework.bean.UserServiceInterceptor;
import com.vow.springframework.event.CustomEvent;
import com.vow.springframework.context.support.ClassPathXmlApplicationContext;
import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: wushaopeng
 * @date: 2022/11/22 15:32
 */
public class AppTest {

    @Test
    public void test_autoProxy() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);

        System.out.println(userService.queryUserInfo());
    }

}
