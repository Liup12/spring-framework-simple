package com.xuande.spring.context.support;

import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.factory.ConfigurableListableBeanFactory;
import com.xuande.spring.beans.factory.config.BeanFactoryPostProcessor;
import com.xuande.spring.beans.factory.config.BeanPostProcessor;
import com.xuande.spring.context.ApplicationEvent;
import com.xuande.spring.context.ApplicationListener;
import com.xuande.spring.context.ConfigurationApplicationContext;
import com.xuande.spring.context.event.ApplicationEventMulticaster;
import com.xuande.spring.context.event.ContextClosedEvent;
import com.xuande.spring.context.event.ContextRefreshedEvent;
import com.xuande.spring.context.event.SimpleApplicationEventMulticaster;
import com.xuande.spring.core.io.DefaultResourceLoader;
import com.xuande.spring.core.io.Resource;

import java.util.Collection;
import java.util.Map;

/**
 * @author : xuande
 * @date : 2022-02-19 17:03
 **/
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurationApplicationContext {

    private ApplicationEventMulticaster applicationEventMulticaster;

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    @Override
    public void refresh() throws BeansException {

        // 1、创建beanFactory，并加载beanDefinition
        refreshBeanFactory();

        // 2、获取beanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 3、添加ApplicationContextAwareProcessor, 让继承自 ApplicationContextAware 的 Bean对象都能感知到自己所属的applicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 4、在实例化bean之前执行BeanFactoryPostProcessor （执行在应用上下文中注册的前置处理bean）
        invokeBeanFactoryPostProcessors(beanFactory);

        // 5、BeanPostProcessor 需要提前与其他 Bean 对象实例化之前执行
        registerBeanPostProcessor(beanFactory);

        // 6、初始化事件发布者
        initApplicationEventMulticaster();

        // 7、注册事件监听器
        registerListener();

        // 8、提前实例化单例Bean对象
        beanFactory.preInstantiateSingletons();

        // 9、发布容器刷新完成事件
        finishRefresh();
    }

    protected abstract void refreshBeanFactory() throws BeansException;

    protected abstract ConfigurableListableBeanFactory getBeanFactory();


    public void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessorBeanFactory(beanFactory);
        }
    }


    public void registerBeanPostProcessor(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }


    private void initApplicationEventMulticaster(){
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingletonBean(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    private void registerListener(){
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener applicationListener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(applicationListener);
        }
    }

    private void finishRefresh(){
        publishEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    @Override
    public Object genBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String name, Object... args) throws BeansException {
        return getBeanFactory().getBean(name, args);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(requiredType);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        //发布容器关闭监听器
        publishEvent(new ContextClosedEvent(this));

        getBeanFactory().destroySingletons();
    }
}
