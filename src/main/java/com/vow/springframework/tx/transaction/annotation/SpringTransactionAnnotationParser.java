package com.vow.springframework.tx.transaction.annotation;

import com.vow.springframework.core.annotation.AnnotatedElementUtils;
import com.vow.springframework.core.annotation.AnnotationAttributes;
import com.vow.springframework.tx.transaction.interceptor.RollbackRuleAttribute;
import com.vow.springframework.tx.transaction.interceptor.RuleBasedTransactionAttribute;
import com.vow.springframework.tx.transaction.interceptor.TransactionAttribute;

import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: wushaopeng
 * @date: 2023/1/13 13:38
 */
public class SpringTransactionAnnotationParser implements TransactionAnnotationParser, Serializable {

    @Override
    public TransactionAttribute parseTransactionAnnotation(AnnotatedElement element) {
        AnnotationAttributes attributes = AnnotatedElementUtils.findMergedAnnotationAttributes(element, Transactional.class, false, false);
        if (null != attributes) {
            return parseTransactionAnnotation(attributes);
        } else {
            return null;
        }
    }

    public TransactionAttribute parseTransactionAnnotation(AnnotationAttributes attributes) {
        RuleBasedTransactionAttribute rbta = new RuleBasedTransactionAttribute();

        List<RollbackRuleAttribute> rollbackRules = new ArrayList<>();
        for (Class<?> rbRule : attributes.getClassArray("rollbackFor")) {
            rollbackRules.add(new RollbackRuleAttribute(rbRule));
        }

        rbta.setRollbackRules(rollbackRules);
        return rbta;
    }
}
