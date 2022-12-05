package com.vow.springframework.context.support;

import com.vow.springframework.beans.BeansException;
import com.vow.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.vow.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.vow.springframework.beans.factory.config.BeanPostProcessor;
import com.vow.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.vow.springframework.context.ApplicationEvent;
import com.vow.springframework.context.ApplicationListener;
import com.vow.springframework.context.ConfigurableApplicationContext;
import com.vow.springframework.context.event.ApplicationEventMulticaster;
import com.vow.springframework.context.event.ContextClosedEvent;
import com.vow.springframework.context.event.ContextRefreshedEvent;
import com.vow.springframework.context.event.SimpleApplicationEventMulticaster;
import com.vow.springframework.core.io.DefaultResourceLoader;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    private static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    protected abstract void refreshBeanFacoory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(requiredType);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public void refresh() throws BeansException {
        // 1.创建 BeanFactory，并加载 BeanDefinition
        refreshBeanFacoory();

        // 2.获取Beanfactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3.添加ApplicationContextAwareProcessor，让继承自ApplicationContextAware接口的bean对象都能感知所属的ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 4.在baan实例化之前，执行 BeanFactoryPostProcessor (Invoke factory processors registered as beans in the context.)
        invokeBeanFactoryPostProcessor(beanFactory);

        // 5.BeanPostProcessor 需要提前于其他bean对象实例化之前执行注册操作
        registerBeanPostProcessor(beanFactory);

        // 6.初始化事件发布者
        initApplicationEventMulticaster();

        // 7.注册事件监听器
        registerApplicationListeners();

        // 8.提前实例化单例bean对象
        beanFactory.preInstantiateSingletons();

        // 9.发布容器刷新完成事件
        finishRefresh();
    }

    private void invokeBeanFactoryPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    private void registerBeanPostProcessor(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    private void registerApplicationListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener applicationListener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(applicationListener);
        }
    }

    private void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    @Override
    public void registerShutdownHook() {
        /**
         * 在jvm中增加一个关闭的钩子，当jvm关闭的时候，会执行系统中已经设置的所有通过方法addShutdownHook添加的钩子，
         * 当系统执行完这些钩子后，jvm才会关闭。所以这些钩子可以在jvm关闭的时候进行内存清理、对象销毁等操作。
         */
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        // 执行销毁单例bean的销毁方法
        getBeanFactory().destroySingletons();
    }
}
