package com.xuande.spring.beans.factory.support;

import com.xuande.spring.core.io.DefaultResourceLoader;
import com.xuande.spring.core.io.ResourceLoader;

/**
 * @author : xuande
 * @date : 2022-02-19 14:35
 **/
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{

    private final BeanDefinitionRegistry registry;


    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegister() {
        return this.registry;
    }
}
