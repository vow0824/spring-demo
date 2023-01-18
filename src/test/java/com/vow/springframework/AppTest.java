package com.vow.springframework;

import com.alibaba.fastjson.JSON;
import com.vow.springframework.beans.factory.BeanFactory;
import com.vow.springframework.context.support.ClassPathXmlApplicationContext;
import com.vow.springframework.dao.IUserDao;
import com.vow.springframework.po.User;
import org.junit.Test;

/**
 * @author: wushaopeng
 * @date: 2022/11/22 15:32
 */
public class AppTest {

    @Test
    public void test_ClassPathXmlApplicationContext() {
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserDao userDao = beanFactory.getBean("IUserDao", IUserDao.class);
        User user = userDao.queryUserInfoById(1L);
        System.out.println("测试结果：" + JSON.toJSONString(user));
    }
}
