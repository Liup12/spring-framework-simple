package com.xuande.spring.beans;

import com.xuande.spring.beans.factory.config.BeanPostProcessor;

/**
 * @author : xuande
 * @date : 2022-02-20 15:50
 **/
public class TestBeanPostprocessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        System.out.println("bean name '" + beanName + "' will initialize");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        System.out.println("bean name '" + beanName + "' initialized");
        return bean;
    }
}
