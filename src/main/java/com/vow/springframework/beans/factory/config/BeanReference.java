package com.vow.springframework.beans.factory.config;

/**
 * @author: wushaopeng
 * @date: 2022/11/23 15:55
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
