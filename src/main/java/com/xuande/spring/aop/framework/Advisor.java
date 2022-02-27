package com.xuande.spring.aop.framework;

import org.aopalliance.aop.Advice;

/**
 * @author : xuande
 * @date : 2022-02-27 15:51
 **/
public interface Advisor {

    Advice getAdvice();
}
