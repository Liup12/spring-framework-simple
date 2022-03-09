package com.xuande.spring.aop;

import com.xuande.spring.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author : xuande
 * @date : 2022-03-09 21:22
 **/
@Component("fatherServiceBeforeAdvice")
public class FatherServiceBeforeAdvice implements MethodBeforeAdvice{
    @Override
    public void before(Method method, Object object, Object[] args) throws Throwable {
        System.out.println("你已被代理");
    }
}
