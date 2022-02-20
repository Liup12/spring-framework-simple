package com.xuande.spring.beans.factory.support;

import cn.hutool.core.util.StrUtil;
import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.factory.DisposableBean;
import com.xuande.spring.beans.factory.config.BeanDefinition;

import java.lang.reflect.Method;

/**
 * @author : xuande
 * @date : 2022-02-20 15:05
 **/
public class DisposableBeanAdapter implements DisposableBean {


    private final String beanName;

    private final Object bean;

    private String destroyMethodName;

    public DisposableBeanAdapter(String beanName, Object bean, BeanDefinition beanDefinition) {
        this.beanName = beanName;
        this.bean = bean;
        this.destroyMethodName = beanDefinition.getDestroyMethodName();
    }

    @Override
    public void destroy() throws Exception {
        if (this.bean instanceof DisposableBean){
            ((DisposableBean) bean).destroy();
        }
        if (StrUtil.isNotEmpty(destroyMethodName) && !(bean instanceof DisposableBean)){
            Method method = bean.getClass().getMethod(destroyMethodName);

            if (null == method){
                throw new BeansException("Couldn't find a destroy method named '" + destroyMethodName + "' on bean with name '" + beanName + "'");
            }
            method.invoke(bean);
        }
    }
}
