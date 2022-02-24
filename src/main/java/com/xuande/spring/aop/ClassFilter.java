package com.xuande.spring.aop;

/**
 * @author : xuande
 * @date : 2022-02-24 20:39
 **/
public interface ClassFilter {
    
    boolean matches(Class<?> clazz);

}
