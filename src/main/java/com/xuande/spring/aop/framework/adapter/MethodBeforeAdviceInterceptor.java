package com.xuande.spring.aop.framework.adapter;

import com.xuande.spring.aop.MethodBeforeAdvice;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @author : xuande
 * @date : 2022-02-27 18:12
 **/
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {

    private MethodBeforeAdvice beforeAdvice;

    public MethodBeforeAdviceInterceptor() {
    }

    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice beforeAdvice) {
        this.beforeAdvice = beforeAdvice;
    }

    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        beforeAdvice.before(methodInvocation.getMethod(), methodInvocation.getThis(), methodInvocation.getArguments());
        return methodInvocation.proceed();
    }
}
