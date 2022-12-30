package com.vow.springframework.beans.factory;

import com.vow.springframework.beans.BeansException;

/**
 * @author: wushaopeng
 * @date: 2022/12/30 11:48
 */
public interface ObjectFactory<T> {
    T getObject() throws BeansException;
}
