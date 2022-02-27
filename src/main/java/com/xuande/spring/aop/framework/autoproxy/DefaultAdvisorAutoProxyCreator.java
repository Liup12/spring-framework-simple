package com.xuande.spring.aop.framework.autoproxy;

import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.factory.BeanFactory;
import com.xuande.spring.beans.factory.BeanFactoryAware;
import com.xuande.spring.beans.factory.config.InstantiationAwareBeanPostProcessor;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/2/25  16:21:15
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor,BeanFactoryAware {

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
        return null;
    }
}
