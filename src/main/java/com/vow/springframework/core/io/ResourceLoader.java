package com.vow.springframework.core.io;

/**
 * @author: wushaopeng
 * @date: 2022/11/24 9:54
 */
public interface ResourceLoader {

    /**
     * Pseudo URL prefix for loading from the class path: "classpath:"
     */
    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
