package com.vow.springframework.bean;

/**
 * @author: wushaopeng
 * @date: 2022/11/22 15:20
 */
public class UserService {

    private String userId;

    private UserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(userId);
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
}
