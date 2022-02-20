package com.xuande.spring.beans.factory.support;

import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.factory.config.BeanDefinition;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;

import java.lang.reflect.Constructor;

/**
 * @author : xuande
 * @date : 2022-02-19 10:36
 **/
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor constructor, Object[] args) throws BeansException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback(new NoOp() {
            @Override
            public int hashCode() {
                return super.hashCode();
            }
        });

        if (constructor == null){
            return enhancer.create();
        }
        return enhancer.create(constructor.getParameterTypes(), args);
    }
}
