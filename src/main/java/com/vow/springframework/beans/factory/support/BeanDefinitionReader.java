package com.vow.springframework.beans.factory.support;

import com.vow.springframework.beans.BeansException;
import com.vow.springframework.core.io.Resource;
import com.vow.springframework.core.io.ResourceLoader;

public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;
}
