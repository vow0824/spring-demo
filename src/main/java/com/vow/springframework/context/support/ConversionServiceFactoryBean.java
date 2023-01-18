package com.vow.springframework.context.support;

import com.sun.istack.internal.Nullable;
import com.vow.springframework.beans.factory.FactoryBean;
import com.vow.springframework.beans.factory.InitializingBean;
import com.vow.springframework.core.convert.ConversionService;
import com.vow.springframework.core.convert.converter.Converter;
import com.vow.springframework.core.convert.converter.ConverterFactory;
import com.vow.springframework.core.convert.converter.ConverterRegistry;
import com.vow.springframework.core.convert.converter.GenericConverter;
import com.vow.springframework.core.convert.support.DefaultConversionService;
import com.vow.springframework.core.convert.support.GenericConversionService;

import java.util.Set;

/**
 * @author: wushaopeng
 * @date: 2023/1/3 15:48
 */
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {

    private Set<?> converters;

    private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    @Override
    public Class<?> getObjectType() {
        return conversionService.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.conversionService = new DefaultConversionService();
        registerConverters(converters, conversionService);
    }

    private void registerConverters(Set<?> converters, ConverterRegistry registry) {
        if (converters != null) {
            for (Object converter : converters) {
                if (converter instanceof GenericConverter) {
                    registry.addConverter((GenericConverter) converter);
                } else if (converter instanceof Converter<?, ?>) {
                    registry.addConverter((Converter<?, ?>) converter);
                } else if (converter instanceof ConverterFactory<?, ?>) {
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                } else {
                    throw new IllegalArgumentException("Each converter object must implement one of the " +
                            "Converter, ConverterFactory, or GenericConverter interfaces");
                }
            }
        }
    }

    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }
}
