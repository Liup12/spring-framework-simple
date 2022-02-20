package com.xuande.spring;

import cn.hutool.core.io.IoUtil;
import com.xuande.spring.beans.factory.support.DefaultListableBeanFactory;
import com.xuande.spring.beans.factory.xml.XmlBeanDefinitionReader;
import com.xuande.spring.core.io.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;


/**
 * @author : xuande
 * @date : 2022-02-19 15:23
 **/
public class LoadTest {

    ResourceLoader resourceLoader;

    @Before
    public void init(){
        resourceLoader = new DefaultResourceLoader();
    }

    @Test
    public void testLoadXml(){

        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader definitionReader = new XmlBeanDefinitionReader(beanFactory);

        definitionReader.loadBeanDefinitions("classpath:spring.xml");


        UserService userService = beanFactory.getBean("userService", UserService.class);

        userService.selectUser();
    }

    @Test
    public void testLoadProperties() throws IOException {

        Resource resource = resourceLoader.getResource("classpath:application.properties");


        InputStream inputStream = resource.getInputStream();

        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }

    @Test
    public void testLoadFileSystem() throws IOException {

        Resource resource = resourceLoader.getResource("C:\\Users\\13193\\Desktop\\application.properties");

        InputStream inputStream = resource.getInputStream();

        System.out.println(IoUtil.readUtf8(inputStream));
    }
}
