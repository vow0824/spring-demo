package com.vow.springframework.bean;

/**
 * @author: wushaopeng
 * @date: 2022/11/30 11:15
 */
public interface IUserService {

    String queryUserInfo();

    String register(String userName);
}
