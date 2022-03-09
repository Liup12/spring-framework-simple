package com.xuande.spring;

import com.xuande.spring.beans.factory.FactoryBean;
import com.xuande.spring.dao.IProductDao;
import com.xuande.spring.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : xuande
 * @date : 2022-02-21 20:21
 **/
public class ProxyBeanFactory implements FactoryBean<IProductDao> {


    @Override
    public IProductDao getObject() throws Exception {
        InvocationHandler handler = (proxy, method, args) -> {

            // 添加排除方法
            if ("toString".equals(method.getName())) return this.toString();

            Map<String, String> hashMap = new HashMap<>();
            hashMap.put("10001", "1000.00");
            hashMap.put("10002", "2000.00");
            hashMap.put("10003", "3000.00");

            return "你被代理了 " + method.getName() + "：" + hashMap.get(args[0].toString());
        };

        return (IProductDao) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                new Class[]{IProductDao.class}, handler);
    }

    @Override
    public Class<?> getObjectType() {
        return IProductDao.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
