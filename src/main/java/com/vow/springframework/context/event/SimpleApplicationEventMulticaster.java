package com.vow.springframework.context.event;

import com.vow.springframework.beans.factory.BeanFactory;
import com.vow.springframework.context.ApplicationEvent;
import com.vow.springframework.context.ApplicationListener;

/**
 * Simple implementation of the {@link ApplicationEventMulticaster} interface.
 * @author: wushaopeng
 * @date: 2022/11/29 17:20
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void multicastEvent(final ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
