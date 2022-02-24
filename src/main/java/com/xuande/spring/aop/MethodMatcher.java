package com.xuande.spring.aop;

import java.lang.reflect.Method;

/**
 * @author : xuande
 * @date : 2022-02-24 20:40
 **/
public interface MethodMatcher {

    boolean matches(Method method, Class<?> clazz);

}
