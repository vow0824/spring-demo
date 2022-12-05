package com.vow.springframework.bean;

import com.vow.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class UserDao {

    private static final Map<String, String> map = new HashMap<>();

    static {
        map.put("101", "hello");
        map.put("102", "world");
        map.put("103", "vow");
    }

    public String queryUserInfo(String userId) {
        return map.get(userId);
    }
}
