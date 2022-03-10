package com.xuande.spring;

import com.xuande.spring.aop.FatherServiceBeforeAdvice;
import com.xuande.spring.context.support.ClasspathXmlApplicationContext;
import com.xuande.spring.service.IFatherService;
import com.xuande.spring.service.ISonService;
import org.junit.Test;

/**
 * spring bean 循环依赖
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/3/8  09:39:08
 */
public class CircleReferenceTest {


    @Test
    public void testCircle(){
        ClasspathXmlApplicationContext applicationContext = new ClasspathXmlApplicationContext(new String[]{"classpath:spring-scan.xml"});
        IFatherService fatherService = applicationContext.getBean(IFatherService.class);
        ISonService sonService = applicationContext.getBean(ISonService.class);
        System.out.println(fatherService.getFatherName());
        System.out.println(sonService.getSonName());
    }


    @Test
    public void testCircleAop(){
        ClasspathXmlApplicationContext applicationContext = new ClasspathXmlApplicationContext(new String[]{"classpath:spring-circle.xml"});
        IFatherService fatherService = applicationContext.getBean(IFatherService.class);
        ISonService sonService = applicationContext.getBean(ISonService.class);
        System.out.println(fatherService.getFatherName());
        System.out.println(sonService.getSonName());
    }
}
