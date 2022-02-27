package com.xuande.spring.aop.aspectj;

import com.xuande.spring.aop.PointCut;
import com.xuande.spring.aop.PointCutAdvisor;
import org.aopalliance.aop.Advice;

/**
 * @author : xuande
 * @date : 2022-02-27 15:50
 **/
public class AspectJExpressionPointCutAdvisor implements PointCutAdvisor {

    /**
     * 切面
     */
    private AspectJExpressionPointCut expressionPointCut;

    /**
     * 拦截方法
     */
    private Advice advice;

    /**
     * 表达式
     */
    private String expression;

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public PointCut getPointCut() {

        if (expressionPointCut == null){
            return new AspectJExpressionPointCut(expression);
        }
        return expressionPointCut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }
}
