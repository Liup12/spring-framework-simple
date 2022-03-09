package com.xuande.spring.aop;

import com.xuande.spring.context.support.ClasspathXmlApplicationContext;
import com.xuande.spring.service.IShopService;
import org.junit.Test;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/3/8  09:06:53
 */
public class SpringAopAutowire {


    @Test
    public void testAutowire(){
        ClasspathXmlApplicationContext applicationContext = new ClasspathXmlApplicationContext(new String[]{"classpath:spring-aop.xml","classpath:spring-scan.xml"});

        IShopService shopService = applicationContext.getBean(IShopService.class);

        System.out.println(shopService.queryShopToken());

    }
}
