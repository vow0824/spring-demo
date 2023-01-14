package com.vow.springframework.tx.transaction.annotation;

import com.vow.springframework.tx.transaction.interceptor.TransactionAttribute;

import java.lang.reflect.AnnotatedElement;

/**
 * 用于解析已知事务注释类型的策略接口
 * @author: wushaopeng
 * @date: 2023/1/13 13:35
 */
public interface TransactionAnnotationParser {

    /**
     * 基于该解析器理解的注释类型，解析给定方法或类的事务属性。
     */
    TransactionAttribute parseTransactionAnnotation(AnnotatedElement element);
}
