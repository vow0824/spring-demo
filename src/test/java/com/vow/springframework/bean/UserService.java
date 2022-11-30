package com.vow.springframework.bean;

import com.vow.springframework.beans.BeansException;
import com.vow.springframework.beans.factory.*;
import com.vow.springframework.context.ApplicationContext;
import com.vow.springframework.context.ApplicationContextAware;

import java.util.Random;

/**
 * @author: wushaopeng
 * @date: 2022/11/22 15:20
 */
public class UserService implements IUserService {

    @Override
    public String queryUserInfo() {
        try {
            Thread.sleep(new Random(1).nextInt(100));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "user:vow,hangzhou";
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
