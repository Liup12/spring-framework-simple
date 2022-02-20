package com.xuande.spring.beans.factory.support;

import com.xuande.spring.beans.factory.config.BeanDefinition;

/**
 * @author : xuande
 * @date : 2022-02-19 10:14
 **/
public interface BeanDefinitionRegistry {

    /**
     * 注册bean定义信息
     * @param beanName
     * @param beanDefinition
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    boolean containsBeanDefinition(String beanName);
}
