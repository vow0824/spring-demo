package com.vow.springframework.context;

import com.vow.springframework.beans.BeansException;
import com.vow.springframework.beans.factory.Aware;

/**
 * Interface to be implemented by any object that wishes to be notified
 * of the {@link ApplicationContext} that it runs in.
 * <p>
 * 实现此接口，既能感知到所属的 ApplicationContext
 */
public interface ApplicationContextAware extends Aware {

    void setApplicatioinContext(ApplicationContext applicationContext) throws BeansException;
}
