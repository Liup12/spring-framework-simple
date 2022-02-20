package com.xuande.spring.beans.factory;

import com.xuande.spring.beans.BeansException;

/**
 * @author : xuande
 * @date : 2022-02-20 14:24
 **/
public interface InitializingBean {

    void afterPropertiesSet() throws BeansException;

}
