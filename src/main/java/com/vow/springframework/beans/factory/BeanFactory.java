package com.vow.springframework.beans.factory;

import com.vow.springframework.beans.BeansException;

/**
 * @author: wushaopeng
 * @date: 2022/11/22 15:13
 */
public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    Object getBean(String beanName, Object... args) throws BeansException;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
