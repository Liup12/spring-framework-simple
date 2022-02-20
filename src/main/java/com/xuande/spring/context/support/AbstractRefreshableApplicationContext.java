package com.xuande.spring.context.support;

import cn.hutool.core.bean.BeanException;
import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.factory.ConfigurableListableBeanFactory;
import com.xuande.spring.beans.factory.support.DefaultListableBeanFactory;

/**
 * @author : xuande
 * @date : 2022-02-19 17:42
 **/
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    private DefaultListableBeanFactory beanFactory;


    private DefaultListableBeanFactory createBeanFactory(){
        return new DefaultListableBeanFactory();
    }

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinition(beanFactory);
        this.beanFactory = beanFactory;
    }

    public abstract void loadBeanDefinition(DefaultListableBeanFactory beanFactory) throws BeanException;


    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return this.beanFactory;
    }
}
