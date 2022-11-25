package com.vow.springframework.beans.factory;

import com.vow.springframework.beans.BeansException;
import com.vow.springframework.beans.factory.config.AutowireCapableBeanFactory;
import com.vow.springframework.beans.factory.config.BeanDefinition;
import com.vow.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author: wushaopeng
 * @date: 2022/11/24 10:09
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String name) throws BeansException;
}
