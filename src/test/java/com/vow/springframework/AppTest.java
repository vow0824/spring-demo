package com.vow.springframework;

import com.vow.springframework.context.support.ClassPathXmlApplicationContext;
import com.vow.springframework.jdbc.core.JdbcTemplate;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * @author: wushaopeng
 * @date: 2022/11/22 15:32
 */
public class AppTest {

    private JdbcTemplate jdbcTemplate;

    @Before
    public void init() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
    }

    @Test
    public void test_execute() {
        jdbcTemplate.execute("insert into user (id, userId, userHead, createTime, updateTime) values (1, '184172133','01_50', now(), now())");
    }

    @Test
    public void test_query() {
        List<Map<String, Object>> allResult = jdbcTemplate.queryForList("select * from user");
        for (Map<String, Object> objectMap : allResult) {
            System.out.println("测试结果：" + objectMap);
        }
    }
}
