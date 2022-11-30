package com.vow.springframework;

import com.vow.springframework.aop.AdvisedSupport;
import com.vow.springframework.aop.MethodMatcher;
import com.vow.springframework.aop.TargetSource;
import com.vow.springframework.aop.aspectj.AspectJExpressionPointcut;
import com.vow.springframework.aop.framework.Cglib2AopProxy;
import com.vow.springframework.aop.framework.JdkDynamicAopProxy;
import com.vow.springframework.aop.framework.ReflectiveMethodInvocation;
import com.vow.springframework.bean.IUserService;
import com.vow.springframework.bean.UserService;
import com.vow.springframework.bean.UserServiceInterceptor;
import com.vow.springframework.event.CustomEvent;
import com.vow.springframework.context.support.ClassPathXmlApplicationContext;
import org.aopalliance.intercept.MethodInterceptor;
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
    public void test_aop() throws NoSuchMethodException {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut("execution(* com.vow.springframework.bean.UserService.*(..))");

        Class<UserService> clazz = UserService.class;
        Method queryUserInfo = clazz.getDeclaredMethod("queryUserInfo");

        System.out.println(pointcut.matches(clazz));
        System.out.println(pointcut.matches(queryUserInfo, clazz));
    }

    @Test
    public void test_dynamic() {
        // 目标对象
        IUserService userService = new UserService();
        // 组装代理信息
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(userService));
        advisedSupport.setMethodInterceptor(new UserServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointcut("execution(* com.vow.springframework.bean.UserService.*(..))"));

        // 代理对象（JdkDynamicAopProxy）
        IUserService proxy_jdk = (IUserService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        System.out.println(proxy_jdk.queryUserInfo());

        // 代理对象（Cglib2AopProxy）
        IUserService proxy_cglib = (IUserService) new Cglib2AopProxy(advisedSupport).getProxy();
        System.out.println(proxy_cglib.register("huahua"));
    }

    @Test
    public void test_proxy_class() {
        IUserService userService = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{IUserService.class}, (proxy, method, args) -> "you are proxied");
        System.out.println(userService.queryUserInfo());
    }

    @Test
    public void test_proxy_method() {
        // 目标对象（可以替换成任何的目标对象）
        Object targetObject = new UserService();

        IUserService userService = (IUserService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), targetObject.getClass().getInterfaces(), new InvocationHandler() {

            // 方法匹配器
            MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* com.vow.springframework.bean.IUserService.*(..))");

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (methodMatcher.matches(method, targetObject.getClass())) {
                    MethodInterceptor methodInterceptor = methodInvocation -> {
                        long start = System.currentTimeMillis();
                        try {
                            return methodInvocation.proceed();
                        } finally {
                            System.out.println("monitor - Begin by aop");
                            System.out.println("method name:" + methodInvocation.getMethod());
                            System.out.println("time consuming:" + (System.currentTimeMillis() - start) + "ms");
                            System.out.println("monitor - End");
                        }
                    };
                    // 反射调用
                    return methodInterceptor.invoke(new ReflectiveMethodInvocation(targetObject, method, args));
                }
                return method.invoke(targetObject, args);
            }
        });

        System.out.println(userService.queryUserInfo());
    }
}
