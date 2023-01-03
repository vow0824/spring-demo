package com.vow.springframework.core.convert.support;

import com.vow.springframework.core.convert.converter.ConverterRegistry;

/**
 * @author: wushaopeng
 * @date: 2023/1/3 15:40
 */
public class DefaultConversionService extends GenericConversionService{

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
    }
}
