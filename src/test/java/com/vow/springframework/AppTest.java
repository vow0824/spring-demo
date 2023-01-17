package com.vow.springframework;

import com.alibaba.fastjson.JSON;
import com.vow.middleware.mybatis.Resources;
import com.vow.middleware.mybatis.SqlSession;
import com.vow.middleware.mybatis.SqlSessionFactory;
import com.vow.middleware.mybatis.SqlSessionFactoryBuilder;
import com.vow.springframework.po.User;
import org.junit.Test;

import java.io.Reader;
import java.util.List;

/**
 * @author: wushaopeng
 * @date: 2022/11/22 15:32
 */
public class AppTest {

    @Test
    public void test_queryUserInfoById() {
        String resource = "mybatis-config-datasource.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);

            SqlSession session = sqlMapper.openSession();
            try {
                User user = session.selectOne("cn.bugstack.middleware.mybatis.test.dao.IUserDao.queryUserInfoById", 1L);
                System.out.println(JSON.toJSONString(user));
            } finally {
                session.close();
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test_queryUserList() {
        String resource = "mybatis-config-datasource.xml";
        Reader reader;
        try {
            reader = Resources.getResourceAsReader(resource);
            SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader);

            SqlSession session = sqlMapper.openSession();
            try {
                User req = new User();
                req.setUserId("184172133");
                List<User> userList = session.selectList("cn.bugstack.middleware.mybatis.test.dao.IUserDao.queryUserList", req);
                System.out.println(JSON.toJSONString(userList));
            } finally {
                session.close();
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
