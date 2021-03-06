package com.xuande.spring.beans.factory.support;

import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.factory.FactoryBean;
import com.xuande.spring.beans.factory.config.BeanDefinition;
import com.xuande.spring.beans.factory.config.BeanPostProcessor;
import com.xuande.spring.beans.factory.config.ConfigurableBeanFactory;
import com.xuande.spring.core.convert.ConversionService;
import com.xuande.spring.util.ClassUtils;
import com.xuande.spring.util.StringValueResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : xuande
 * @date : 2022-02-19 10:02
 **/
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport implements ConfigurableBeanFactory {

    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>();

    ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();

    private ConversionService conversionService;

    @Override
    public Object genBean(String beanName) throws BeansException {
        return doGetBean(beanName,  null);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return doGetBean(beanName,args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    @Override
    public boolean containsBean(String beanName) {
        return containsBeanDefinition(beanName);
    }

    /**
     *
     * @param beanName
     * @param args
     * @param <T>
     * @return
     */
    public <T> T doGetBean(final String beanName, final Object [] args){
        Object singletonBean = getSingletonBean(beanName);

        if (singletonBean != null){
            return (T) getObjectForBeanInstance(singletonBean, beanName);
        }

        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        Object bean = createBean(beanName, beanDefinition, args);

        return (T) getObjectForBeanInstance(bean, beanName);
    }

    private Object getObjectForBeanInstance(Object beanInstance, String beanName){
        if (!(beanInstance instanceof FactoryBean)){
            return beanInstance;
        }

        Object object = getCachedObjectForFactoryBean(beanName);

        if (null == object){
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            object = getObjectFromFactoryBean(factoryBean, beanName);
        }
        return object;
    }


    protected abstract BeanDefinition getBeanDefinition(String beanName);


    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object [] args) throws BeansException;

    protected abstract boolean containsBeanDefinition(String beanName);

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        beanPostProcessorList.remove(beanPostProcessor);
        beanPostProcessorList.add(beanPostProcessor);
    }

    public List<BeanPostProcessor> getBeanPostProcessorList() {
        return beanPostProcessorList;
    }


    public ClassLoader getBeanClassLoader() {
        return beanClassLoader;
    }

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        embeddedValueResolvers.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : this.embeddedValueResolvers) {
            result = resolver.resolverStringValue(result);
        }
        return result;
    }

    @Override
    public void setConversionService(ConversionService service) {
        this.conversionService = service;
    }

    @Override
    public ConversionService getConversionService() {
        return this.conversionService;
    }
}
