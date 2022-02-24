package com.xuande.spring.aop;

/**
 * @author : xuande
 * @date : 2022-02-24 20:49
 **/
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    public Class<?> [] getTargetClass(){
        return this.target.getClass().getInterfaces();
    }

    public Object getTarget() {
        return this.target;
    }
}
