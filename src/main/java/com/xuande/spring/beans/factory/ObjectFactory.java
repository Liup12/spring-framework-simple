package com.xuande.spring.beans.factory;

import com.xuande.spring.beans.BeansException;

/**
 * @author : xuande
 * @date : 2022-03-09 20:30
 **/
public interface ObjectFactory<T> {

    T getObject() throws BeansException;

}
