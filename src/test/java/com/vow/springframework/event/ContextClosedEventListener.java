package com.vow.springframework.event;

import com.vow.springframework.context.ApplicationEvent;
import com.vow.springframework.context.ApplicationListener;
import com.vow.springframework.context.event.ContextClosedEvent;

public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {
    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("context closed event:" + this.getClass().getName());
    }
}
