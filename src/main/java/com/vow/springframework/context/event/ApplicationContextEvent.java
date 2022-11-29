package com.vow.springframework.context.event;

import com.vow.springframework.context.ApplicationContext;
import com.vow.springframework.context.ApplicationEvent;

/**
 * Base class for events raised for an <code>ApplicationContext</code>.
 *
 * @author: wushaopeng
 * @date: 2022/11/29 16:51
 */
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    /**
     * Get the <code>ApplicationContext</code> that the event was raised for.
     * @return
     */
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
