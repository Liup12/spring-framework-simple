package com.xuande.spring.aop;

import java.lang.reflect.Method;

/**
 * @author : xuande
 * @date : 2022-02-27 17:26
 **/
public interface MethodBeforeAdvice extends BeforeAdvice{

    void before(Method method, Object object, Object [] args) throws Throwable;


}
