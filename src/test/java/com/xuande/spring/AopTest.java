package com.xuande.spring;

import com.xuande.spring.aop.AdvisedSupport;
import com.xuande.spring.aop.MethodMatcher;
import com.xuande.spring.aop.TargetSource;
import com.xuande.spring.aop.aspectj.AspectJExpressionPointCut;
import com.xuande.spring.aop.framework.Cglib2AopProxy;
import com.xuande.spring.aop.framework.JDKDynamicAopProxy;
import com.xuande.spring.service.IShopService;
import com.xuande.spring.service.ProductService;
import com.xuande.spring.service.impl.ShopService;
import org.junit.Test;

import java.lang.reflect.Method;

/**
 * @author : xuande
 * @date : 2022-02-24 21:17
 **/
public class AopTest {

    @Test
    public void test_aop() throws NoSuchMethodException {


        MethodMatcher methodMatcher = new AspectJExpressionPointCut("execution(* com.xuande.spring.UserService.*(..))");

        Class<UserService> userServiceClass = UserService.class;
        Method userSer = userServiceClass.getMethod("sayHello");

        Class<ProductService> productServiceClass = ProductService.class;

        Method queryPrice = productServiceClass.getMethod("queryPrice", String.class);


        System.out.println(methodMatcher.matches(userSer, userServiceClass));
        System.out.println(methodMatcher.matches(queryPrice, productServiceClass));
    }


    @Test
    public void test_dynamic(){
        IShopService shopService = new ShopService();
        AdvisedSupport advisedSupport = new AdvisedSupport();
        advisedSupport.setTargetSource(new TargetSource(shopService));
        advisedSupport.setMethodInterceptor(new ShopServiceInterceptor());
        advisedSupport.setMethodMatcher(new AspectJExpressionPointCut("execution(* com.xuande.spring.service.IShopService.*(..))"));

        IShopService shopService_jdk = (IShopService) new JDKDynamicAopProxy(advisedSupport).getProxy();

        System.out.println(shopService_jdk.queryShopInfo());

        IShopService shopService_cglib = (IShopService) new Cglib2AopProxy(advisedSupport).getProxy();

        System.out.println(shopService_cglib.queryShopInfo());
    }


}
