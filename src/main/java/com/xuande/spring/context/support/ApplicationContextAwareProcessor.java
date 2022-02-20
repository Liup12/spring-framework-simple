package com.xuande.spring.context.support;

import com.xuande.spring.beans.factory.config.BeanPostProcessor;
import com.xuande.spring.context.ApplicationContext;
import com.xuande.spring.context.ApplicationContextAware;

/**
 * @author : xuande
 * @date : 2022-02-20 16:40
 **/
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;


    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (bean instanceof ApplicationContextAware){
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }

}
