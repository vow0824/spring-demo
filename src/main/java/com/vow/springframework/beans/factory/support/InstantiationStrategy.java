package com.vow.springframework.beans.factory.support;

import com.vow.springframework.beans.BeansException;
import com.vow.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @author: wushaopeng
 * @date: 2022/11/22 17:17
 */
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException;
}
