package com.vow.springframework.beans.factory.config;

/**
 * @author: wushaopeng
 * @date: 2022/11/22 15:48
 */
public interface SingletonBeanRegistry {

    Object getSingletonObj(String beanName);
}
