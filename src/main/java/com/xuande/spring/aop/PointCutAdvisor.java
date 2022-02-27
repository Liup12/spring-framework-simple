package com.xuande.spring.aop;

import com.xuande.spring.aop.framework.Advisor;

/**
 * @author : xuande
 * @date : 2022-02-27 15:51
 **/
public interface PointCutAdvisor extends Advisor {

    PointCut getPointCut();
}
