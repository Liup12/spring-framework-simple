package com.xuande.spring.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.PropertyValue;
import com.xuande.spring.beans.PropertyValues;
import com.xuande.spring.beans.factory.*;
import com.xuande.spring.beans.factory.config.AutowireCapableBeanFactory;
import com.xuande.spring.beans.factory.config.BeanDefinition;
import com.xuande.spring.beans.factory.config.BeanPostProcessor;
import com.xuande.spring.beans.factory.config.BeanReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author : xuande
 * @date : 2022-02-19 10:13
 **/
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    @Override
    public Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {

        Object bean = null;
        try {
            // 实例化bean
            bean = createBeanInstance(beanDefinition, beanName, args);
            // 给 Bean 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
            // 初始化bean
            initializeBean(beanName, bean, beanDefinition);
        }catch (Exception e){
            throw new BeansException("Instantiation of bean failed", e);
        }


        //注册实现了DisposableBean接口的bean
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        addSingletonBean(beanName, bean);

        return bean;
    }

    /**
     * 注册需要回调销毁方法的bean
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    private void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition){
        if ((bean instanceof DisposableBean) || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())){
            registerDisposableBean(beanName, new DisposableBeanAdapter(beanName, bean, beanDefinition));
        }
    }

    /**
     * 筛选bean对象构造器
     * @param beanDefinition
     * @param beanName
     * @param args
     * @return
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object [] args){
        Constructor constructorToUse = null;
        Class clazz = beanDefinition.getBeanClass();
        Constructor[] constructors = clazz.getDeclaredConstructors();
        for (Constructor constructor : constructors) {
            if (args !=null && constructor.getParameterCount() == args.length){
                constructorToUse = constructor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }


    /**
     * 给bean对象填充属性
     * @param beanName
     * @param bean
     * @param beanDefinition
     */
    protected void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition){
        try {
            PropertyValues propertyValues = beanDefinition.getPropertyValues();

            PropertyValue[] propertyValueList = propertyValues.getPropertyValueList();


            for (PropertyValue propertyValue : propertyValueList) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                if (value instanceof BeanReference){
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getName());
                }

                BeanUtil.setFieldValue(bean, name, value);
            }
        }catch (Exception e){
            throw new BeansException("Error setting property values: " + beanName);
        }

    }


    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }


    /**
     * 初始化bean
     * 1、执行beanPostprocessor 的bean初始化前置处理
     * 2、初始化bean
     * 3、执行beanPostprocessor 的Bean初始化后置处理
     * @param beanName
     * @param bean
     * @param beanDefinition
     * @return
     */
    Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition){

        if (bean instanceof Aware){
            if (bean instanceof BeanFactoryAware){
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
            if (bean instanceof BeanClassLoaderAware){
                ((BeanClassLoaderAware) bean).setBeanClassLoader(getBeanClassLoader());
            }
            if (bean instanceof BeanNameAware){
                ((BeanNameAware) bean).setBeanName(beanName);
            }
        }

        // 执行bean初始化方法前回调
        Object wrappedBean = applyBeanPostprocessorBeforeInitialization(bean, beanName);

        try {
            // 执行初始化方法
            invokeInitMethods(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean [" + beanName + "] failed", e);
        }

        // 执行bean初始化方法后回调
        wrappedBean = applyBeanPostprocessorAfterInitialization(wrappedBean, beanName);

        return wrappedBean;
    }

    /**
     * 执行初始化方法
     * @param beanName
     * @param bean
     * @param beanDefinition
     * @throws Exception
     */
    private void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Exception {

        // 实现InitializingBean接口
        if (bean instanceof InitializingBean){
            ((InitializingBean) bean).afterPropertiesSet();
        }

        // 注解配置，判断避免二次执行初始化方法
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName) && !(bean instanceof InitializingBean)){

            Method method = beanDefinition.getBeanClass().getMethod(beanDefinition.getInitMethodName());
            if (null == method){
                throw new BeansException("cloud not find an init method named '" + initMethodName + "' on bean with name '" + beanName + "'");
            }
            method.invoke(bean);

        }

    }

    @Override
    public Object applyBeanPostprocessorBeforeInitialization(Object existingBean, String beanName) throws BeanException {
        Object result = existingBean;
        List<BeanPostProcessor> beanPostProcessorList = getBeanPostProcessorList();
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            Object current = beanPostProcessor.postProcessBeforeInitialization(result, beanName);
            if (null == current){
                return result;
            }
            result = current;
        }
        return result;
    }


    @Override
    public Object applyBeanPostprocessorAfterInitialization(Object existingBean, String beanName) throws BeanException {
        Object result = existingBean;
        List<BeanPostProcessor> beanPostProcessorList = getBeanPostProcessorList();
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorList) {
            Object current = beanPostProcessor.postProcessAfterInitialization(result, beanName);
            if (null == current){
                return result;
            }
            result = current;
        }
        return result;
    }
}
