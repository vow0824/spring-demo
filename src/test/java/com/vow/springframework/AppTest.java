package com.vow.springframework;

import cn.hutool.core.io.IoUtil;
import com.vow.springframework.bean.UserDao;
import com.vow.springframework.bean.UserService;
import com.vow.springframework.beans.PropertyValue;
import com.vow.springframework.beans.PropertyValues;
import com.vow.springframework.beans.factory.BeanFactory;
import com.vow.springframework.beans.factory.config.BeanDefinition;
import com.vow.springframework.beans.factory.config.BeanReference;
import com.vow.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.vow.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.vow.springframework.context.support.ClassPathXmlApplicationContext;
import com.vow.springframework.core.io.DefaultResourceLoader;
import com.vow.springframework.core.io.Resource;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Before;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: wushaopeng
 * @date: 2022/11/22 15:32
 */
public class AppTest {

    @Test
    public void test_prototype() {
        // 1.初始化BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2.获取bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        UserService userService1 = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService);
        System.out.println(userService1);

        System.out.println(userService + "十六进制哈希：" + Integer.toHexString(userService.hashCode()));
        System.out.println(ClassLayout.parseInstance(userService).toPrintable());
    }

    @Test
    public void test_factory_bean() {
        // 1.初始化BeanFactory
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        applicationContext.registerShutdownHook();

        // 2.获取bean对象调用方法
        UserService userService = applicationContext.getBean("userService", UserService.class);
        System.out.println(userService.queryUserInfo());
    }
}
