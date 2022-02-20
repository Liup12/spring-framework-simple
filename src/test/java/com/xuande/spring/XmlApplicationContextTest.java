package com.xuande.spring;

import com.xuande.spring.context.support.ClasspathXmlApplicationContext;
import org.junit.Test;

/**
 * @author : xuande
 * @date : 2022-02-19 17:57
 **/
public class XmlApplicationContextTest {


    @Test
    public void testRefreshContext(){
        ClasspathXmlApplicationContext applicationContext = new ClasspathXmlApplicationContext(new String[]{"classpath:spring.xml","classpath:spring-config.xml"});
        applicationContext.registerShutdownHook();
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.selectUser();
    }
}
