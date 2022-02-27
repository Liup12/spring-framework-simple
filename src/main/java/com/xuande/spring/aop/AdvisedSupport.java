package com.xuande.spring.aop;


import org.aopalliance.intercept.MethodInterceptor;

/**
 * @author : xuande
 * @date : 2022-02-24 20:51
 **/
public class AdvisedSupport {

    // 被代理目标对象
    private TargetSource targetSource;
    // 代理拦截器
    private MethodInterceptor methodInterceptor;
    // 方法匹配器（判断是否满足通知条件）
    private MethodMatcher methodMatcher;

    private boolean isProxyTargetClass;

    public AdvisedSupport() {
    }

    public boolean isProxyTargetClass() {
        return isProxyTargetClass;
    }

    public void setProxyTargetClass(boolean proxyTargetClass) {
        isProxyTargetClass = proxyTargetClass;
    }

    public AdvisedSupport(TargetSource targetSource, MethodInterceptor methodInterceptor, MethodMatcher methodMatcher) {
        this.targetSource = targetSource;
        this.methodInterceptor = methodInterceptor;
        this.methodMatcher = methodMatcher;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}
