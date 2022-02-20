package com.xuande.spring.context;

import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.factory.Aware;

/**
 * @author : xuande
 * @date : 2022-02-20 16:39
 **/
public interface ApplicationContextAware extends Aware {


    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;

}
