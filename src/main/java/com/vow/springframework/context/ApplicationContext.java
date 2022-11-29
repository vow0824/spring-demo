package com.vow.springframework.context;

import com.vow.springframework.beans.factory.HierarchicalBeanFactory;
import com.vow.springframework.beans.factory.ListableBeanFactory;
import com.vow.springframework.core.io.ResourceLoader;

/**
 * Central interface to provide configuration for an application.
 * This is read-only while the application is running, but may be
 * reloaded if the implementation supports this.
 *
 * 应用上下文
 */
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher {
}
