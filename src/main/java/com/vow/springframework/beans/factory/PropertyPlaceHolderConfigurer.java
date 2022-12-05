package com.vow.springframework.beans.factory;

import com.vow.springframework.beans.BeansException;
import com.vow.springframework.beans.PropertyValue;
import com.vow.springframework.beans.PropertyValues;
import com.vow.springframework.beans.factory.config.BeanDefinition;
import com.vow.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.vow.springframework.beans.factory.config.BeanPostProcessor;
import com.vow.springframework.core.io.DefaultResourceLoader;
import com.vow.springframework.core.io.Resource;
import com.vow.springframework.util.StringValueResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * Allows for configuration of individual bean property values from a property resource,
 * i.e. a properties file. Useful for custom config files targeted at system
 * administrators that override bean properties configured in the application context.
 *
 * @author: wushaopeng
 * @date: 2022/12/5 16:20
 */
public class PropertyPlaceHolderConfigurer implements BeanFactoryPostProcessor {

    /**
     * Default placeholder prefix: {@value}
     */
    private static final String DEFAULT_PLACEHOLDER_PREFIX = "${";

    /**
     * Default placeholder suffix: {@value}
     */
    private static final String DEFAULT_PLACEHOLDER_SUFFIX = "}";

    private String location;

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());

            String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
            for (String beanName : beanDefinitionNames) {
                BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
                PropertyValues propertyValues = beanDefinition.getPropertyValues();

                for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
                    Object value = propertyValue.getValue();
                    if (!(value instanceof String)) continue;
                    value = resolvePlaceHolder((String) value, properties);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), value));
                }
            }

            // 向容器中添加字符串解析器，供解析@Value注解使用
            StringValueResolver resolver = new PlaceHolderResolvingStringValueResolver(properties);
            beanFactory.addEmbeddedValueResolver(resolver);

        } catch (IOException e) {
            throw new BeansException("Could not load properties", e);
        }
    }

    private String resolvePlaceHolder(String value, Properties properties) {
        String strVal = value;
        StringBuffer stringBuffer = new StringBuffer(strVal);
        int startIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_PREFIX);
        int stopIdx = strVal.indexOf(DEFAULT_PLACEHOLDER_SUFFIX);
        if (startIdx != -1 && stopIdx != -1 && startIdx < stopIdx) {
            String propertyKey = strVal.substring(startIdx + 2, stopIdx);
            String propertyVal = properties.getProperty(propertyKey);
            stringBuffer.replace(startIdx, stopIdx + 1, propertyVal);
        }
        return stringBuffer.toString();
    }

    private class PlaceHolderResolvingStringValueResolver implements StringValueResolver {

        private final Properties properties;

        public PlaceHolderResolvingStringValueResolver(Properties properties) {
            this.properties = properties;
        }

        @Override
        public String resolveStringValue(String strVal) {
            return PropertyPlaceHolderConfigurer.this.resolvePlaceHolder(strVal, properties);
        }
    }
}
