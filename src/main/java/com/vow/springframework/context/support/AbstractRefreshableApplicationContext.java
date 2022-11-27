package com.vow.springframework.context.support;

import com.vow.springframework.beans.BeansException;
import com.vow.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.vow.springframework.beans.factory.support.DefaultListableBeanFactory;

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFacoory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinition(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinition(DefaultListableBeanFactory beanFactory);

    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
