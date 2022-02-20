package com.xuande.spring;

import com.xuande.spring.beans.PropertyValue;
import com.xuande.spring.beans.PropertyValues;
import com.xuande.spring.beans.factory.config.BeanDefinition;
import com.xuande.spring.beans.factory.config.BeanReference;
import com.xuande.spring.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

/**
 * @author : xuande
 * @date : 2022-02-19 10:50
 **/
public class SpringTest {


    @Test
    public void testInstanceBean(){

        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();


        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);

        defaultListableBeanFactory.registerBeanDefinition("userService", beanDefinition);


        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService", "臭宝");

        userService.sayHello();

    }


    @Test
    public void testSetProperties(){
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();


        BeanDefinition beanDefinition2 = new BeanDefinition(UserDao.class);

        defaultListableBeanFactory.registerBeanDefinition("userDao", beanDefinition2);

        PropertyValues propertyValues = new PropertyValues();

        PropertyValue propertyValue = new PropertyValue("name", "臭宝");
        PropertyValue propertyValue2 = new PropertyValue("userDao", new BeanReference("userDao"));

        propertyValues.addPropertyValue(propertyValue);
        propertyValues.addPropertyValue(propertyValue2);

        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        defaultListableBeanFactory.registerBeanDefinition("userService", beanDefinition);

        UserService userService = (UserService) defaultListableBeanFactory.getBean("userService");

        userService.selectUser();
    }
}
