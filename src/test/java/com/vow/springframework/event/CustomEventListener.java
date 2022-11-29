package com.vow.springframework.event;

import com.vow.springframework.context.ApplicationListener;
import com.vow.springframework.event.CustomEvent;

import java.util.Date;

public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("received:" + event.getSource() + " message; time:" + new Date());
        System.out.println("message:" + event.getId() + ":" + event.getMessage());
    }
}
