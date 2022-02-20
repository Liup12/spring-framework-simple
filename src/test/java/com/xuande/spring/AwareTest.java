package com.xuande.spring;

import com.xuande.spring.context.support.ClasspathXmlApplicationContext;
import org.junit.Test;

/**
 * @author : xuande
 * @date : 2022-02-20 16:57
 **/
public class AwareTest {

    @Test
    public void testAware(){
        ClasspathXmlApplicationContext applicationContext = new ClasspathXmlApplicationContext("classpath:spring.xml");


        applicationContext.registerShutdownHook();

        TeacherService teacherService = applicationContext.getBean("teacherService", TeacherService.class);

        int i = 0;
    }
}
