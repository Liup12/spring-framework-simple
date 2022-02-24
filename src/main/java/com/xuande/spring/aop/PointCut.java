package com.xuande.spring.aop;

/**
 * @author : xuande
 * @date : 2022-02-24 20:41
 **/
public interface PointCut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();

}
