package com.xuande.spring.beans.factory.support;

import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.factory.ConfigurableListableBeanFactory;
import com.xuande.spring.beans.factory.config.BeanDefinition;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : xuande
 * @date : 2022-02-19 10:51
 **/
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory  {

    Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();


    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);

        if (beanDefinition == null){
            throw new BeansException("no such bean named" + beanName + "is defined");
        }
        return beanDefinition;
    }

    @Override
    public void preInstantiateSingletons() throws BeansException {
        beanDefinitionMap.keySet().forEach(this::getBean);
    }


    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName,beanDefinition)->{
            Class clazz = beanDefinition.getBeanClass();
            if (type.isAssignableFrom(clazz)){
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return beanDefinitionMap.keySet().toArray(new String[0]);
    }


}
