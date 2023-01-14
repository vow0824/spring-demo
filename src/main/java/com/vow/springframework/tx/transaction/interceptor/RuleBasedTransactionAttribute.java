package com.vow.springframework.tx.transaction.interceptor;

import java.io.Serializable;
import java.util.List;

/**
 * TransactionAttribute implementation that works out whether a given exception
 * should cause transaction rollback by applying a number of rollback rules,
 * both positive and negative. If no custom rollback rules apply, this attribute
 * behaves like DefaultTransactionAttribute (rolling back on runtime exceptions).
 * @author: wushaopeng
 * @date: 2023/1/13 14:46
 */
public class RuleBasedTransactionAttribute extends DefaultTransactionAttribute implements Serializable {

    private List<RollbackRuleAttribute> rollbackRules;

    public RuleBasedTransactionAttribute() {
        super();
    }

    public void setRollbackRules(List<RollbackRuleAttribute> rollbackRules) {
        this.rollbackRules = rollbackRules;
    }
}
