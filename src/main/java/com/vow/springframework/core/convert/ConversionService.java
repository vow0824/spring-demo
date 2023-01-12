package com.vow.springframework.core.convert;

import com.sun.istack.internal.Nullable;

/**
 * @author: wushaopeng
 * @date: 2023/1/3 14:46
 */
public interface ConversionService {

    /** Return {@code true} if objects of {@code sourceType} can be converted to the {@code targetType}. */
    boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType);

    /** Convert the given {@code source} to the specified {@code targetType}. */
    <T> T convert(Object source, Class<T> targetType);
}