package com.vow.springframework.util;

import com.vow.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * Simple strategy interface for resolving a String value.
 * Used by {@link ConfigurableBeanFactory}.
 */
public interface StringValueResolver {

    String resolveStringValue(String strVal);
}
