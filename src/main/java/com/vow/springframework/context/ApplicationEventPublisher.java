package com.vow.springframework.context;

/**
 * Interface that encapsulates event publication functionality.
 * Serves as super-interface for ApplicationContext.
 * <p>
 * 事件发布者接口
 *
 * @author: wushaopeng
 * @date: 2022/11/29 16:45
 */
public interface ApplicationEventPublisher {

    /**
     * Notify all listeners registered with this application of an application
     * event. Events may be framework events (such as RequestHandledEvent)
     * or application-specific events.
     * @param event the event to publish
     */
    void publishEvent(ApplicationEvent event);
}
