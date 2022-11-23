package com.vow.springframework.beans.factory.support;

import com.vow.springframework.beans.factory.config.BeanDefinition;

/**
 * @author: wushaopeng
 * @date: 2022/11/22 15:51
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册中心注册 beanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
