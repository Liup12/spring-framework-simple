package com.xuande.spring.beans.factory.annotation;

import cn.hutool.core.bean.BeanUtil;
import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.PropertyValues;
import com.xuande.spring.beans.factory.BeanFactory;
import com.xuande.spring.beans.factory.BeanFactoryAware;
import com.xuande.spring.beans.factory.ConfigurableListableBeanFactory;
import com.xuande.spring.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.xuande.spring.util.ClassUtils;

import java.lang.reflect.Field;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/3/1  15:20:53
 */
public class AutowiredAnnotationBeanPostProcessor implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    ConfigurableListableBeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;
    }

    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();
        beanClass = ClassUtils.isCglibProxyClass(beanClass) ? beanClass.getSuperclass() : beanClass;
        Field[] fields = beanClass.getDeclaredFields();
        for (Field field : fields) {
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (null != valueAnnotation){
                String value = valueAnnotation.value();
                value = beanFactory.resolveEmbeddedValue(value);
                BeanUtil.setProperty(bean, field.getName(), value);
            }
        }


        for (Field field : fields) {
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if (null != autowiredAnnotation){
                Class<?> fieldType = field.getType();
                String dependentBeanName = null;
                Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
                Object dependentBean = null;
                if (null != qualifierAnnotation) {
                    dependentBeanName = qualifierAnnotation.value();
                    dependentBean = beanFactory.getBean(dependentBeanName, fieldType);
                } else {
                    dependentBean = beanFactory.getBean(fieldType);
                }
                BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
            }
        }
        return pvs;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return null;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return null;
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) {
        return true;
    }


}
