package com.vow.springframework.event;

import com.vow.springframework.context.ApplicationEvent;
import com.vow.springframework.context.ApplicationListener;
import com.vow.springframework.context.event.ContextRefreshedEvent;

public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("refreshed event:" + this.getClass().getName());
    }
}
