package com.vow.springframework.stereotype;

import java.lang.annotation.*;

/**
 * @author: wushaopeng
 * @date: 2022/12/5 14:02
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";
}
