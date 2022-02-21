package com.xuande.spring.beans.factory.support;

import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/2/21  09:35:42
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry{


    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    /**
     * 获取缓存FactoryBean
     * @param beanName
     * @return
     */
    protected Object getCachedObjectForFactoryBean(String beanName){
        Object bean = this.factoryBeanObjectCache.get(beanName);
        return bean != NULL_OBJECT ? bean : null;
    }

    /**
     * 获取FactoryBean对象
     *
     * 如果是单例bean先从缓存中获取, 缓存中取不到再去创建bean
     *
     * 如果是原型bean直接去查询创建代理对象
     *
     * @param factoryBean
     * @param beanName
     * @return
     */
    protected Object getObjectFromFactoryBean(FactoryBean factoryBean, String beanName){
        if (factoryBean.isSingleton()){
            Object object = this.factoryBeanObjectCache.get(beanName);
            if (null == object){
                object = doGetObjectFromFactoryBean(factoryBean, beanName);
                this.factoryBeanObjectCache.put(beanName, object != null ? object : NULL_OBJECT);
            }
            return object != NULL_OBJECT ? object : null;
        }else {
            return doGetObjectFromFactoryBean(factoryBean, beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factoryBean, final String beanName){

        try {
            return factoryBean.getObject();
        } catch (Exception e) {
            throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
        }
    }
}
