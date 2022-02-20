package com.xuande.spring.beans.factory.config;

/**
 * bean前置处理
 * @author : xuande
 * @date : 2022-02-19 17:19
 **/
public interface BeanPostProcessor {

    /**
     * bean实例化前置处理
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessBeforeInitialization(Object bean, String beanName);


    /**
     * bean实例化后置处理
     * @param bean
     * @param beanName
     * @return
     */
    Object postProcessAfterInitialization(Object bean, String beanName);


}
