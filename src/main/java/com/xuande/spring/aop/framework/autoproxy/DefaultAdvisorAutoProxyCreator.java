package com.xuande.spring.aop.framework.autoproxy;

import com.xuande.spring.aop.AdvisedSupport;
import com.xuande.spring.aop.ClassFilter;
import com.xuande.spring.aop.PointCut;
import com.xuande.spring.aop.TargetSource;
import com.xuande.spring.aop.aspectj.AspectJExpressionPointCutAdvisor;
import com.xuande.spring.aop.framework.Advisor;
import com.xuande.spring.aop.framework.ProxyFactory;
import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.PropertyValues;
import com.xuande.spring.beans.factory.BeanFactory;
import com.xuande.spring.beans.factory.BeanFactoryAware;
import com.xuande.spring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.xuande.spring.beans.factory.support.DefaultListableBeanFactory;
import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;

import java.util.Collection;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/2/25  16:21:15
 */
public class DefaultAdvisorAutoProxyCreator implements InstantiationAwareBeanPostProcessor,BeanFactoryAware {

    private DefaultListableBeanFactory beanFactory;


    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (DefaultListableBeanFactory) beanFactory;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
        return null;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        return pvs;
    }

    /**
     * 剔除拦截方法，切面，advisor
     * @param clazz
     * @return
     */
    private boolean isInfrastructureClass(Class<?> clazz){
        return Advice.class.isAssignableFrom(clazz) || PointCut.class.isAssignableFrom(clazz) || Advisor.class.isAssignableFrom(clazz);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (isInfrastructureClass(bean.getClass())){
            return null;
        }
        Collection<AspectJExpressionPointCutAdvisor> pointCutAdvisors = beanFactory.getBeansOfType(AspectJExpressionPointCutAdvisor.class).values();
        for (AspectJExpressionPointCutAdvisor pointCutAdvisor : pointCutAdvisors) {
            ClassFilter classFilter = pointCutAdvisor.getPointCut().getClassFilter();
            if (!classFilter.matches(bean.getClass())){
                continue;
            }
            TargetSource targetSource = null;

            targetSource = new TargetSource(bean);

            AdvisedSupport advisedSupport = new AdvisedSupport();
            advisedSupport.setTargetSource(targetSource);
            advisedSupport.setMethodInterceptor((MethodInterceptor) pointCutAdvisor.getAdvice());
            advisedSupport.setMethodMatcher(pointCutAdvisor.getPointCut().getMethodMatcher());
            advisedSupport.setProxyTargetClass(false);
            return new ProxyFactory(advisedSupport).getProxy();
        }
        return bean;
    }


}
