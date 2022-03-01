package com.xuande.spring;

import com.xuande.spring.context.support.ClasspathXmlApplicationContext;
import com.xuande.spring.service.ITradeService;
import org.junit.Test;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/3/1  10:59:52
 */
public class ComponentTest {

    /**
     * 自动扫描组件
     */
    @Test
    public void testComponent(){
        String [] path = {"classpath:spring-scan.xml"};
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext(path);
        ITradeService tradeService = context.getBean("tradeService", ITradeService.class);

        System.out.println(tradeService);
    }

    /**
     * 自动注入属性
     */
    @Test
    public void testValue(){
        String [] path = {"classpath:spring-property.xml"};
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext(path);
        ITradeService tradeService = context.getBean("tradeService", ITradeService.class);
        System.out.println(tradeService);
    }
}
