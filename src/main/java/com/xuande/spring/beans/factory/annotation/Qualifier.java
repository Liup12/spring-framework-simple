package com.xuande.spring.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/3/1  15:18:07
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Qualifier {
    String value() default "";
}
