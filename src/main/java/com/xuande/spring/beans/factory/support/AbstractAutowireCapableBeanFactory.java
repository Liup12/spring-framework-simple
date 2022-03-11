package com.xuande.spring.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.TypeUtil;
import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.PropertyValue;
import com.xuande.spring.beans.PropertyValues;
import com.xuande.spring.beans.factory.*;
import com.xuande.spring.beans.factory.config.*;
import com.xuande.spring.core.convert.ConversionService;

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
        Object bean = resolveBeforeInstantiation(beanName, beanDefinition);
        if (bean != null){
            return bean;
        }
        return doCreateBean(beanName, beanDefinition, args);

    }

    /**
     * 创建bean
     */
    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition, Object[] args){
        Object bean = null;
        try {
            // 实例化bean
            bean = createBeanInstance(beanDefinition, beanName, args);

            //提前暴露缓存，解决循环依赖

            if (beanDefinition.isSingleton()){
                Object finalBean = bean;
                addSingletonFactory(beanName, () -> getEarlyBeanReference(beanName, beanDefinition, finalBean));
            }

            // 实例化后判断是否需要属性填充
            boolean continueWithPropertyPopulation = applyBeanPostProcessorAfterInstantiation(beanName, bean);
            if (!continueWithPropertyPopulation){
                return bean;
            }

            // 在设置Bean属性之前，允许BeanPostProcessor修改属性值
            applyBeanPostProcessorsBeforeApplyingPropertyValues(beanName, bean, beanDefinition);
            // 给 Bean 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
            // 初始化bean （普通bean\代理Bean）
            bean = initializeBean(beanName, bean, beanDefinition);
        }catch (Exception e){
            throw new BeansException("Instantiation of bean failed", e);
        }


        //注册实现了DisposableBean接口的bean
        registerDisposableBeanIfNecessary(beanName, bean, beanDefinition);

        Object exposedObject = bean;
        if (beanDefinition.isSingleton()){
            exposedObject = getSingletonBean(beanName);
            registerSingletonBean(beanName, exposedObject);
        }

        return exposedObject;

    }


    protected Object getEarlyBeanReference(String beanName, BeanDefinition beanDefinition, Object bean){
        Object exposedObject = bean;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessorList()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                exposedObject = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).getEarlyBeanReference(bean, beanName);
                if (exposedObject == null){
                    return exposedObject;
                }
            }
        }
        return exposedObject;
    }

    /**
     * 判断是否继续属性填充
     * @param beanName
     * @param bean
     * @return
     */
    protected boolean applyBeanPostProcessorAfterInstantiation(String beanName, Object bean){
        boolean continueWithPropertyPopulation = true;
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessorList()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                if (!((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessAfterInstantiation(bean, beanName)) {
                    continueWithPropertyPopulation = false;
                    break;
                }
            }
        }
        return continueWithPropertyPopulation;
    }

    protected void applyBeanPostProcessorsBeforeApplyingPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessorList()) {
            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                PropertyValues pvs = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessPropertyValues(beanDefinition.getPropertyValues(), bean, beanName);
                if (null != pvs) {
                    for (PropertyValue propertyValue : pvs.getPropertyValueList()) {
                        beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                    }
                }
            }
        }
    }


    protected Object resolveBeforeInstantiation(String beanName, BeanDefinition beanDefinition){
        //实例化前
        Object bean = applyBeanPostProcessorBeforeInstantiation(beanDefinition.getBeanClass(), beanName);
        if (bean != null){
            // 初始化后
            bean = applyBeanPostprocessorAfterInitialization(bean, beanName);
        }
        return bean;

    }

    protected Object applyBeanPostProcessorBeforeInstantiation(Class<?> clazz, String beanName){
        for (BeanPostProcessor beanPostProcessor : getBeanPostProcessorList()) {

            if (beanPostProcessor instanceof InstantiationAwareBeanPostProcessor){
                Object bean = ((InstantiationAwareBeanPostProcessor) beanPostProcessor).postProcessBeforeInstantiation(clazz, beanName);
                if (bean != null){
                    return bean;
                }
            }
        }
        return null;
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
                }else {
                    Class<?> sourceType = value.getClass();
                    Class<?> targetType = (Class<?>) TypeUtil.getFieldType(bean.getClass(), name);
                    ConversionService conversionService = getConversionService();
                    if (conversionService.canConvert(sourceType, targetType)) {
                        value = conversionService.convert(value, targetType);
                    }
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
            invokeInitMethods(beanName, wrappedBean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Invocation of init method of bean [" + beanName + "] failed", e);
        }

        // 执行bean初始化方法后回调(若被切面切中，则返回aop代理对象)
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
