package com.vow.springframework.dao;


import com.vow.springframework.po.User;

public interface IUserDao {

     User queryUserInfoById(Long id);

}
