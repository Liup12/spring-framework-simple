package com.xuande.spring.beans.factory.support;

import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.factory.DisposableBean;
import com.xuande.spring.beans.factory.ObjectFactory;
import com.xuande.spring.beans.factory.config.BeanPostProcessor;
import com.xuande.spring.beans.factory.config.SingletonBeanRegistry;

import java.beans.Beans;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : xuande
 * @date : 2022-02-19 09:55
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    protected static final Object NULL_OBJECT = new Object();

    /**
     * 一级缓存 单例Bean对象
     */
    private Map<String, Object> singletonBeanMap = new ConcurrentHashMap<>();


    /**
     * 二级缓存 未实例化对象
     */
    private Map<String, Object> earlySingletonObjects = new HashMap<>();

    /**
     * 三级缓存 存放代理对象
     */
    private Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();


    private final Map<String, DisposableBean> disposableBeans = new LinkedHashMap<>();

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    @Override
    public void registerSingletonBean(String beanName, Object bean) {
        singletonBeanMap.put(beanName, bean);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> objectFactory){
        if (!singletonBeanMap.containsKey(beanName)){
            singletonFactories.put(beanName, objectFactory);
            earlySingletonObjects.remove(beanName);
        }
    }


    public Object getSingletonBean(String beanName){
        Object singletonBean = singletonBeanMap.get(beanName);

        if (singletonBean == null){
            // 判断二级缓存中是否存在对象
            singletonBean = earlySingletonObjects.get(beanName);
            if (singletonBean == null) {

                // 将三级缓存对象放入二级缓存中
                ObjectFactory<?> objectFactory = singletonFactories.get(beanName);
                if (objectFactory != null){
                    singletonBean = objectFactory.getObject();
                    earlySingletonObjects.put(beanName, singletonBean);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singletonBean;

    }


    public void destroySingletons(){
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableNames = keySet.toArray();
        for (int i = disposableNames.length - 1; i >= 0; i--) {
            Object beanName = disposableNames[i];
            DisposableBean disposableBean = disposableBeans.get(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException("Destroy method on bean with name '" + beanName + "' threw an exception", e);
            }
        }
    }

}
