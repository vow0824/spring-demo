package com.vow.springframework.tx.transaction.annotation;

import java.lang.annotation.*;

/**
 * @author: wushaopeng
 * @date: 2023/1/13 13:32
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Transactional {

    Class<? extends Throwable>[] rollbackFor() default {};

}
