package com.xuande.spring.beans.factory;

import com.xuande.spring.beans.BeansException;

/**
 * @author : xuande
 * @date : 2022-02-19 09:54
 **/
public interface BeanFactory {

    Object genBean(String name) throws BeansException;

    Object getBean(String name, Object ...args) throws BeansException ;

    <T> T getBean(String name, Class<T> requiredType) throws BeansException;

    <T> T getBean(Class<T> requiredType) throws BeansException;

    boolean containsBean(String beanName);
}
