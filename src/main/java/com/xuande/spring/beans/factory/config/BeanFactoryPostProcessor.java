package com.xuande.spring.beans.factory.config;

import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.factory.ConfigurableListableBeanFactory;

/**
 * @author : xuande
 * @date : 2022-02-19 17:21
 **/
public interface BeanFactoryPostProcessor {

    /**
     * 在所有beanDefinition加载完之后，bean实例化之前，提供修改beanDefinition的机制
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
