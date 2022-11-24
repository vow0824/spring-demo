package com.vow.springframework.beans.factory.config;

import com.vow.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * @author: wushaopeng
 * @date: 2022/11/24 10:10
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";
}
