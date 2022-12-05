package com.vow.springframework.context.annotation;

import java.lang.annotation.*;

/**
 * @author: wushaopeng
 * @date: 2022/12/5 14:04
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Scope {

    String value() default "singleton";
}
