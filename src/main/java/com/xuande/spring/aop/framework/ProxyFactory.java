package com.xuande.spring.aop.framework;

import com.xuande.spring.aop.AdvisedSupport;

/**
 * @author : xuande
 * @date : 2022-02-27 16:59
 **/
public class ProxyFactory implements AopProxy{

    private AdvisedSupport advisedSupport;

    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }


    @Override
    public Object getProxy() {
        return createAopProxy();
    }


    private Object createAopProxy(){
        if (advisedSupport.isProxyTargetClass()){
            return new Cglib2AopProxy(advisedSupport).getProxy();
        }
        return new JDKDynamicAopProxy(advisedSupport).getProxy();
    }
}
