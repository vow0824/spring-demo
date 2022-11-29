package com.vow.springframework;

import com.vow.springframework.event.CustomEvent;
import com.vow.springframework.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

/**
 * @author: wushaopeng
 * @date: 2022/11/22 15:32
 */
public class AppTest {

    @Test
    public void test_event() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext, 1019129009086763L, "succeed!"));
        applicationContext.registerShutdownHook();
    }
}
