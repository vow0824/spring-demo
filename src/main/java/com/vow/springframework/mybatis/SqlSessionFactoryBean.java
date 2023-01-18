package com.vow.springframework.mybatis;

import com.vow.middleware.mybatis.SqlSessionFactory;
import com.vow.middleware.mybatis.SqlSessionFactoryBuilder;
import com.vow.springframework.beans.factory.FactoryBean;
import com.vow.springframework.beans.factory.InitializingBean;
import com.vow.springframework.core.io.DefaultResourceLoader;
import com.vow.springframework.core.io.Resource;

import java.io.InputStream;

public class SqlSessionFactoryBean implements FactoryBean<SqlSessionFactory>, InitializingBean {

    private String resource;

    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void afterPropertiesSet() throws Exception {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(this.resource);

        try (InputStream inputStream = resource.getInputStream()) {
            this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public SqlSessionFactory getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }
}
