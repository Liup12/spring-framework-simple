package com.xuande.spring.beans.factory;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/2/21  09:37:03
 */
public interface FactoryBean<T> {

    T getObject() throws Exception;

    Class<?> getObjectType();

    boolean isSingleton();

}
