package com.vow.springframework.core.convert.support;

import com.vow.springframework.core.convert.converter.Converter;
import com.vow.springframework.core.convert.converter.ConverterFactory;
import com.vow.springframework.util.NumberUtils;

/**
 * @author: wushaopeng
 * @date: 2023/1/3 15:42
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {
    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<>(targetType);
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {

        private final Class<T> targetType;

        private StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(String source) {
            if (source == null) {
                return null;
            }
            return NumberUtils.parseNumber(source, this.targetType);
        }
    }
}
