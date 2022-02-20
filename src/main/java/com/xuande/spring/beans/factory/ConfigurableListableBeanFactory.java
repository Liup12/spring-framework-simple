package com.xuande.spring.beans.factory;

import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.xuande.spring.beans.factory.config.BeanDefinition;
import com.xuande.spring.beans.factory.config.BeanPostProcessor;
import com.xuande.spring.beans.factory.config.ConfigurableBeanFactory;

/**
 * @author : xuande
 * @date : 2022-02-19 13:57
 **/
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    void preInstantiateSingletons() throws BeansException;

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

}
