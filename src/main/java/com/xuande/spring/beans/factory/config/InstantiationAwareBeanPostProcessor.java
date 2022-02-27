package com.xuande.spring.beans.factory.config;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/2/25  16:22:27
 */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor{

    /**
     * 实例化之前自动创建动态代理对象
     * @param beanClass
     * @param beanName
     * @return
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName);

}
