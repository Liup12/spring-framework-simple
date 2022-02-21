package com.xuande.spring;

import com.xuande.spring.context.support.ClasspathXmlApplicationContext;
import com.xuande.spring.service.ProductService;
import org.junit.Test;

/**
 * @author : xuande
 * @date : 2022-02-21 20:35
 **/
public class PrototypeBeanTest {

    @Test
    public void testPrototype(){
        ClasspathXmlApplicationContext applicationContext = new ClasspathXmlApplicationContext(new String[]{"classpath:spring2.xml"});
        applicationContext.registerShutdownHook();

        ProductService productService = applicationContext.getBean("productService", ProductService.class);
        ProductService productService2 = applicationContext.getBean("productService", ProductService.class);

        System.out.println(productService.hashCode());
        System.out.println(productService2.hashCode());
    }


    @Test
    public void testProxy(){
        ClasspathXmlApplicationContext applicationContext = new ClasspathXmlApplicationContext(new String[]{"classpath:spring2.xml"});
        applicationContext.registerShutdownHook();

        ProductService productService = applicationContext.getBean("productService", ProductService.class);

        String info = productService.queryPrice("10001");

        System.out.println(info);
    }

}
