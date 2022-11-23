package com.vow.springframework.beans;

/**
 * @author: wushaopeng
 * @date: 2022/11/22 15:45
 */
public class BeansException extends RuntimeException{

    public BeansException(String msg) {
        super(msg);
    }

    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
