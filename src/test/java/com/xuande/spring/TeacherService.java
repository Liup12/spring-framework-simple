package com.xuande.spring;

import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.factory.BeanClassLoaderAware;
import com.xuande.spring.beans.factory.BeanFactory;
import com.xuande.spring.beans.factory.BeanFactoryAware;
import com.xuande.spring.beans.factory.BeanNameAware;
import com.xuande.spring.context.ApplicationContext;
import com.xuande.spring.context.ApplicationContextAware;

/**
 * @author : xuande
 * @date : 2022-02-20 16:51
 **/
public class TeacherService implements BeanNameAware, BeanFactoryAware, ApplicationContextAware, BeanClassLoaderAware {


    private ClassLoader classLoader;

    private ApplicationContext applicationContext;

    private BeanFactory beanFactory;

    private String beanName;

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) throws BeansException {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
