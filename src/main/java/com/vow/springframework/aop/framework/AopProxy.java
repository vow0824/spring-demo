package com.vow.springframework.aop.framework;

/**
 * Delegate interface for a configured AOP proxy, allowing for the creation
 * of actual proxy objects.
 *
 * <p>Out-of-the-box implementations are available for JDK dynamic proxies
 * and for CGLIB proxies, as applied by DefaultAopProxyFactory
 * <p>
 * AOP 代理的抽象
 *
 * @author: wushaopeng
 * @date: 2022/11/30 10:33
 */
public interface AopProxy {

    Object getProxy();
}
