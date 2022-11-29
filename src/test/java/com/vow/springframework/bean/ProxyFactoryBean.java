package com.vow.springframework.bean;

import com.vow.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: wushaopeng
 * @date: 2022/11/29 11:14
 */
public class ProxyFactoryBean implements FactoryBean<UserDao> {
    @Override
    public UserDao getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {
            if ("toString".equals(method.getName())) return this.toString();

            Map<String, Object> map = new HashMap<>();
            map.put("101", "hello");
            map.put("102", "world");
            map.put("103", "vow");

            return "你被代理了" + method.getName() + ":" + map.get(args[0].toString());
        };
        return (UserDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{UserDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return UserDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
