package com.xuande.spring.beans.factory;

import com.xuande.spring.beans.BeansException;
import java.util.Map;

/**
 * @author : xuande
 * @date : 2022-02-19 13:48
 **/
public interface ListableBeanFactory extends BeanFactory{

    /**
     * 按照类型返回 bean 实例
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;


    /**
     * 获取所有bean定义信息
     * @return
     */
    String[] getBeanDefinitionNames();

}
