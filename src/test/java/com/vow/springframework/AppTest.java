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

    private AdvisedSupport advisedSupport;

    @Before
    public void init() {
        // 目标对象
        IUserService userService = new UserService();
        // 组装代理信息
        advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        // 以自定义拦截器的形式拦截
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.vow.springframework.bean.IUserService.*(..))"));
    }

    @Test
    public void test_proxy_factory() {
        advisedSupport.setProxyTargetClass(false);  // false/true   jdk动态代理/cglib动态代理
        IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();

        System.out.println(proxy.queryUserInfo());
    }

    @Test
    public void test_beforeAdvice() {
        UserServiceBeforeAdvice userServiceBeforeAdvice = new UserServiceBeforeAdvice();
        MethodBeforeAdviceInterceptor methodBeforeAdviceInterceptor = new MethodBeforeAdviceInterceptor(userServiceBeforeAdvice);
        // 以advice的形式拦截
        advisedSupport.setMethodInterceptor(methodBeforeAdviceInterceptor);
        IUserService proxy = (IUserService) new ProxyFactory(advisedSupport).getProxy();

        System.out.println(proxy.queryUserInfo());
    }

    @Test
    public void test_advisor() {
        // 目标对象
        IUserService userService = new UserService();

        AspectJExpressionPointcutAdvisor advisor = new AspectJExpressionPointcutAdvisor();
        advisor.setExpression("execution(* com.vow.springframework.bean.IUserService.*(..))");
        advisor.setAdvice(new MethodBeforeAdviceInterceptor(new UserServiceBeforeAdvice()));

        ClassFilter classFilter = advisor.getPointcut().getClassFilter();
        if (classFilter.matches(userService.getClass())) {
            AdvisedSupport support = new AdvisedSupport();
            support.setTargetSource(new TargetSource(userService));
            support.setProxyTargetClass(true);
            support.setMethodInterceptor((MethodInterceptor) advisor.getAdvice());
            support.setMethodMatcher(advisor.getPointcut().getMethodMatcher());

            IUserService proxy = (IUserService) new ProxyFactory(support).getProxy();
            System.out.println(proxy.queryUserInfo());
        }
    }

    @Test
    public void test_aop() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");

        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
    }
}
