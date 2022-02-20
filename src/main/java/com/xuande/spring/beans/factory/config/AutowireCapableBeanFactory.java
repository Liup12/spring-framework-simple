package com.xuande.spring.beans.factory.config;

import cn.hutool.core.bean.BeanException;
import com.xuande.spring.beans.factory.BeanFactory;

/**
 * @author : xuande
 * @date : 2022-02-19 13:43
 **/
public interface AutowireCapableBeanFactory extends BeanFactory {


    /**
     * bean初始化方法执行前回调
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object applyBeanPostprocessorBeforeInitialization(Object existingBean, String beanName) throws BeanException;

    /**
     * bean初始化方法执行后回调
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeanException
     */
    Object applyBeanPostprocessorAfterInitialization(Object existingBean, String beanName) throws BeanException;
}
