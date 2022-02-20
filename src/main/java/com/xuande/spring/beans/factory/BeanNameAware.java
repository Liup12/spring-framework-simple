package com.xuande.spring.beans.factory;

/**
 * @author : xuande
 * @date : 2022-02-20 16:36
 **/
public interface BeanNameAware extends Aware{

    void setBeanName(String name);
}
