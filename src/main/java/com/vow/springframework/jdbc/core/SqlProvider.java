package com.vow.springframework.jdbc.core;

/**
 * Interface to be implemented by objects that can provide SQL strings.
 * @author: wushaopeng
 * @date: 2023/1/11 17:25
 */
public interface SqlProvider {

    String getSql();
}
