package com.vow.springframework.mybatis;

import com.vow.middleware.mybatis.SqlSessionFactory;
import com.vow.springframework.beans.factory.FactoryBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MapperFactoryBean<T> implements FactoryBean<T> {

    private Class<T> mapperInterfaces;

    private SqlSessionFactory sqlSessionFactory;

    public MapperFactoryBean() {
    }

    public MapperFactoryBean(Class<T> mapperInterfaces, SqlSessionFactory sqlSessionFactory) {
        this.mapperInterfaces = mapperInterfaces;
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public T getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {
            if (Object.class.equals(method.getClass())) {
                return method.invoke(this, args);
            }
            try {
                System.out.println("你被代理了，执行SQL操作！" + method.getName());
                return sqlSessionFactory.openSession().selectOne(mapperInterfaces.getName() + "." + method.getName(), args[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return method.getReturnType().newInstance();
        };
        return (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{mapperInterfaces}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return mapperInterfaces;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public Class<T> getMapperInterfaces() {
        return mapperInterfaces;
    }

    public void setMapperInterfaces(Class<T> mapperInterfaces) {
        this.mapperInterfaces = mapperInterfaces;
    }

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
}
