package com.xuande.spring.context.support;

import cn.hutool.core.bean.BeanException;
import com.xuande.spring.beans.factory.support.DefaultListableBeanFactory;
import com.xuande.spring.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @author : xuande
 * @date : 2022-02-19 17:51
 **/
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{


    @Override
    public void loadBeanDefinition(DefaultListableBeanFactory beanFactory) throws BeanException {
        String[] configLocations = getConfigLocations();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        for (String configLocation : configLocations) {
            if (null != configLocation){
                beanDefinitionReader.loadBeanDefinitions(configLocation);
            }
        }
    }


    public abstract String [] getConfigLocations();
}
