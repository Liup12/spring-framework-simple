package com.xuande.spring;

import com.xuande.spring.context.support.ClasspathXmlApplicationContext;
import com.xuande.spring.event.SyncProductEvent;
import org.junit.Test;

/**
 * @author : xuande
 * @date : 2022-02-21 22:05
 **/
public class ApplicationEventTest {

    @Test
    public void testContext(){
        ClasspathXmlApplicationContext applicationContext = new ClasspathXmlApplicationContext(new String[]{"classpath:spring-event.xml"});
        applicationContext.publishEvent(new SyncProductEvent(applicationContext, "10001", "雅顿金胶60粒装"));
        applicationContext.registerShutdownHook();

    }
}
