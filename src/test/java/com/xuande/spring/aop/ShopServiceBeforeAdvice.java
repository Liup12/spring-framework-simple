package com.xuande.spring.aop;

import java.lang.reflect.Method;

/**
 * @author : xuande
 * @date : 2022-02-27 17:28
 **/
public class ShopServiceBeforeAdvice implements MethodBeforeAdvice{

    @Override
    public void before(Method method, Object object, Object[] args) throws Throwable {
        System.out.println("拦截方法：" + method.getName());
    }
}
