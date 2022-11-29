package com.vow.springframework.context.event;

/**
 * Event raised when an <code>ApplicationContext</code> gets initialized or refreshed.
 *
 * @author: wushaopeng
 * @date: 2022/11/29 16:54
 */
public class ContextRefreshedEvent extends ApplicationContextEvent{
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
