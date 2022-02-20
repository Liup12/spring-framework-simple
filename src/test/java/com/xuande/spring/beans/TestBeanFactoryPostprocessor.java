package com.xuande.spring.beans;

import com.xuande.spring.beans.factory.ConfigurableListableBeanFactory;
import com.xuande.spring.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @author : xuande
 * @date : 2022-02-20 15:49
 **/
public class TestBeanFactoryPostprocessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("postProcessorBeanFactory method invoke()");
    }
}
