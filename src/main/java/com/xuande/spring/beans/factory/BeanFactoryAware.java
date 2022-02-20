package com.xuande.spring.beans.factory;

import com.xuande.spring.beans.BeansException;

/**
 * @author : xuande
 * @date : 2022-02-20 16:35
 **/
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
