package com.xuande.spring.beans.factory.support;

import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 初始化bean对象策略
 * @author : xuande
 * @date : 2022-02-19 10:19
 **/
public interface InstantiationStrategy {

    /**
     * 初始化bean对象
     * @param beanDefinition
     * @param beanName
     * @param constructor
     * @param args
     * @return
     * @throws BeansException
     */
    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object [] args) throws BeansException;
}
