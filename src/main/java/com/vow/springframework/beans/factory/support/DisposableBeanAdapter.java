package com.vow.springframework.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.vow.springframework.beans.BeansException;
import com.vow.springframework.beans.factory.DisposableBean;
import com.vow.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author: wushaopeng
 * @date: 2022/11/28 14:17
 */
public class DisposableBeanAdapter implements DisposableBean {

    private final Object bean;

    private final String beanName;

    private String destroyMethodName;

    public DisposableBeanAdapter(Object bean, String beanName, BeanDefinition beanDefinition) {
        this.bean = bean;
        this.beanName = beanName;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        // 1.实现接口 DisposableBean
        if (bean instanceof DisposableBean) {
            ((DisposableBean) bean).destroy();
        }

        // 2.注解配置destroy-method (判断是为了避免二次执行销毁方法)
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean && "destroy".equals(this.destroyMethodName))) {
            Method destroyMethod = bean.getClass().getMethod(this.destroyMethodName);
            if (null == destroyMethod) {
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethod + "' on bean with name '" + beanName + "'");
            }
            destroyMethod.invoke(bean);
        }
    }
}
