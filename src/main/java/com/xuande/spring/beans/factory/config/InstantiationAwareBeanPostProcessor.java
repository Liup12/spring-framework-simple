package com.xuande.spring.beans.factory.config;

import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.PropertyValues;

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

    /**
     * 初始化方法执行之后，执行此方法
     * @param bean
     * @param beanName
     * @return
     */
    boolean postProcessAfterInstantiation(Object bean, String beanName);

    /**
     * 在 Bean 对象实例化完成后，设置属性操作之前执行此方法
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException;
}
