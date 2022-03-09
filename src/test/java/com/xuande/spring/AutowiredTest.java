package com.xuande.spring;

import com.xuande.spring.context.support.ClasspathXmlApplicationContext;
import com.xuande.spring.dao.IProductDao;
import com.xuande.spring.service.ITradeService;
import org.junit.Test;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/3/1  16:39:33
 */
public class AutowiredTest {



    @Test
    public void testAutowired(){
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:spring-scan.xml");
        ITradeService tradeService = context.getBean(ITradeService.class);
        System.out.println(tradeService);
        System.out.println(tradeService.selectByTradeId("11111"));
    }


    @Test
    public void testFactoryBean() throws Exception {
        ClasspathXmlApplicationContext context = new ClasspathXmlApplicationContext("classpath:spring-scan.xml");
        IProductDao bean = (IProductDao) context.getBean("proxyBeanFactory");

        String s = bean.queryPrice("10001");
        System.out.println(s);
    }
}
