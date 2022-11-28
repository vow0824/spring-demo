package com.vow.springframework.bean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wushaopeng
 * @date: 2022/11/23 16:08
 */
public class UserDao {
    private static Map<String, String> map = new HashMap<>();

    /*static {
        map.put("101", "hello");
        map.put("102", "world");
        map.put("103", "vow");
    }*/

    public void initDataMethod() {
        map.put("101", "hello");
        map.put("102", "world");
        map.put("103", "vow");
        System.out.println("execute 'init-method' of UserDao");
    }

    public void destroyDataMethod() {
        System.out.println("execute 'destroy-method' of UserDao");
        map.clear();
    }

    public String queryUserName(String userId) {
        return map.getOrDefault(userId, "none");
    }
}
