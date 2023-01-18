package com.vow.springframework.mybatis;

import cn.hutool.core.lang.ClassScanner;
import com.vow.middleware.mybatis.SqlSessionFactory;
import com.vow.springframework.beans.BeansException;
import com.vow.springframework.beans.PropertyValue;
import com.vow.springframework.beans.PropertyValues;
import com.vow.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.vow.springframework.beans.factory.config.BeanDefinition;
import com.vow.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.vow.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

import java.util.Set;

public class MapperScannerConfigurer implements BeanDefinitionRegistryPostProcessor {

    private String basePackage;

    private SqlSessionFactory sqlSessionFactory;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        try {
            Set<Class<?>> classes = ClassScanner.scanPackage(basePackage);
            for (Class<?> clazz : classes) {
                // 定义bean信息
                BeanDefinition beanDefinition = new BeanDefinition(clazz);
                PropertyValues propertyValues = new PropertyValues();
                propertyValues.addPropertyValue(new PropertyValue("mapperInterface", clazz));
                propertyValues.addPropertyValue(new PropertyValue("sqlSessionFactory", sqlSessionFactory));
                beanDefinition.setPropertyValues(propertyValues);
                beanDefinition.setBeanClass(MapperFactoryBean.class);
                // 注册bean信息
                registry.registerBeanDefinition(clazz.getSimpleName(), beanDefinition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setBasePackage(String basePackage) {
        this.basePackage = basePackage;
    }

    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
