package com.xuande.spring;

import com.xuande.spring.context.support.ClasspathXmlApplicationContext;
import com.xuande.spring.converter.TradeBean;
import org.junit.Test;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/3/11  14:55:45
 */
public class SpringConverterTest {


    @Test
    public void test(){
        ClasspathXmlApplicationContext applicationContext = new ClasspathXmlApplicationContext(new String[]{"classpath:spring-converter.xml"});
        TradeBean bean = applicationContext.getBean(TradeBean.class);

        System.out.println(bean);
    }
}
