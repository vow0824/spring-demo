package com.vow.springframework.bean;

import com.vow.springframework.beans.factory.DisposableBean;
import com.vow.springframework.beans.factory.InitializingBean;

/**
 * @author: wushaopeng
 * @date: 2022/11/22 15:20
 */
public class UserService implements DisposableBean, InitializingBean {

    private String userId;

    private UserDao userDao;

    private String company;

    private String location;

    public String queryUserInfo() {
        return userDao.queryUserName(userId) + "," + company + "," + location;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("execute 'destroy' method of UserService");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("execute 'afterPropertiesSet' method of UserService");
    }
}
