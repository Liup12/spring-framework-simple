package com.xuande.spring.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/3/1  15:18:33
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Value {
    String value();
}
