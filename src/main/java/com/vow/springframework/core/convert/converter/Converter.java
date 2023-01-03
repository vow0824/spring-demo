package com.vow.springframework.core.convert.converter;

/**
 * @author: wushaopeng
 * @date: 2023/1/3 14:53
 */
public interface Converter<S, T> {

    /** Convert the source object of type {@code S} to target type {@code T}. */
    T convert(S source);
}
