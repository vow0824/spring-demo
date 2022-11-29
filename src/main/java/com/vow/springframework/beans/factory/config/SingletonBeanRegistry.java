package com.vow.springframework.beans.factory.config;

/**
 * @author: wushaopeng
 * @date: 2022/11/22 15:48
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);
}
