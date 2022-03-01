package com.xuande.spring.stereotype;

import java.lang.annotation.*;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/3/1  10:33:06
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    String value() default "";

}
