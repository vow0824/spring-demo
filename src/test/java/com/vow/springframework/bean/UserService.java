package com.vow.springframework.bean;

import com.vow.springframework.beans.BeansException;
import com.vow.springframework.beans.factory.*;
import com.vow.springframework.beans.factory.annotation.Autowired;
import com.vow.springframework.beans.factory.annotation.Value;
import com.vow.springframework.context.ApplicationContext;
import com.vow.springframework.context.ApplicationContextAware;
import com.vow.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author: wushaopeng
 * @date: 2022/11/22 15:20
 */
@Component("userService")
public class UserService implements IUserService {

    //@Value("${token}")
    private String token;

    /*@Autowired
    private UserDao userDao;*/

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "token='" + token + '\'' +
                '}';
    }

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        //return userDao.queryUserInfo("103") + "," + token;
        return  "vow," + token;
    }

    @Override
    public String register(String userName) {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "register user：" + userName + "success";
    }
}
