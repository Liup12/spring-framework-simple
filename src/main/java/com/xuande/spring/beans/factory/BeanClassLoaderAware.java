package com.xuande.spring.beans.factory;

import com.xuande.spring.beans.BeansException;

/**
 * @author : xuande
 * @date : 2022-02-20 16:37
 **/
public interface BeanClassLoaderAware extends Aware{

    void setBeanClassLoader(ClassLoader classLoader) throws BeansException;

}
