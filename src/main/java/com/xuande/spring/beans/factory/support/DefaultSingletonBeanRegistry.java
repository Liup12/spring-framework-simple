package com.xuande.spring.beans.factory.support;

import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.factory.DisposableBean;
import com.xuande.spring.beans.factory.config.BeanPostProcessor;
import com.xuande.spring.beans.factory.config.SingletonBeanRegistry;

import java.beans.Beans;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author : xuande
 * @date : 2022-02-19 09:55
 **/
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonBeanMap = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    @Override
    public void addSingletonBean(String beanName, Object bean) {
        singletonBeanMap.put(beanName, bean);
    }


    public Object getSingletonBean(String beanName){
        return singletonBeanMap.get(beanName);
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
