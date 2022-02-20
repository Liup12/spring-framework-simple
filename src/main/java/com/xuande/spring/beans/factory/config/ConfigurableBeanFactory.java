package com.xuande.spring.beans.factory.config;

import com.xuande.spring.beans.factory.HierarchicalBeanFactory;

/**
 * @author : xuande
 * @date : 2022-02-19 13:41
 **/
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);


    void destroySingletons();
}
