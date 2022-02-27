package com.xuande.spring.aop;

import com.xuande.spring.ShopServiceInterceptor;
import com.xuande.spring.aop.aspectj.AspectJExpressionPointCut;
import com.xuande.spring.aop.framework.ProxyFactory;
import com.xuande.spring.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import com.xuande.spring.context.support.ClasspathXmlApplicationContext;
import com.xuande.spring.service.IShopService;
import com.xuande.spring.service.impl.ShopService;
import org.junit.Before;
import org.junit.Test;

/**
 * @author : xuande
 * @date : 2022-02-27 18:03
 **/
public class SpringAopTest {

    private AdvisedSupport advisedSupport;

    @Before
    public void init(){
        IShopService shopService = new ShopService();
        advisedSupport = new AdvisedSupport();
        advisedSupport.setMethodInterceptor(new ShopServiceInterceptor());
        advisedSupport.setTargetSource(new TargetSource(shopService));
        advisedSupport.setMethodMatcher(new AspectJExpressionPointCut("execution(* com.xuande.spring.service.IShopService.*(..))"));
    }

    @Test
    public void test_advice(){
        advisedSupport.setProxyTargetClass(false);
        IShopService shopService = (IShopService) new ProxyFactory(advisedSupport).getProxy();

        System.out.println(shopService.queryShopInfo());
    }

    @Test
    public void test_before_advice(){
        ShopServiceBeforeAdvice shopServiceBeforeAdvice = new ShopServiceBeforeAdvice();

        MethodBeforeAdviceInterceptor interceptor = new MethodBeforeAdviceInterceptor(shopServiceBeforeAdvice);

        advisedSupport.setMethodInterceptor(interceptor);
        IShopService shopService = (IShopService) new ProxyFactory(advisedSupport).getProxy();

        System.out.println(shopService.queryShopInfo());

    }


    @Test
    public void test_spring_aop(){
        ClasspathXmlApplicationContext applicationContext = new ClasspathXmlApplicationContext(new String[]{"classpath:spring-aop.xml"});

        IShopService shopService = applicationContext.getBean("shopService", IShopService.class);

        System.out.println(shopService.queryShopInfo());
    }
}
