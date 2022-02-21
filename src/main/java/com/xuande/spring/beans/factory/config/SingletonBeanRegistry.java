package com.xuande.spring.beans.factory.config;

/**
 * @author : xuande
 * @date : 2022-02-19 09:42
 **/
public interface SingletonBeanRegistry {

    /**
     *
     * @param beanName
     * @param bean
     */
    void registerSingletonBean(String beanName, Object bean);


}
