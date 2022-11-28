package com.vow.springframework.bean;

import com.vow.springframework.beans.BeansException;
import com.vow.springframework.beans.factory.*;
import com.vow.springframework.context.ApplicationContext;
import com.vow.springframework.context.ApplicationContextAware;

/**
 * @author: wushaopeng
 * @date: 2022/11/22 15:20
 */
public class UserService implements BeanNameAware, BeanClassLoaderAware, BeanFactoryAware, ApplicationContextAware {

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

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
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("Classoader:" + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Bean name is '" + name + "'");
    }

    @Override
    public void setApplicatioinContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
