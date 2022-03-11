package com.xuande.spring.beans.factory.config;

import com.xuande.spring.beans.factory.HierarchicalBeanFactory;
import com.xuande.spring.core.convert.ConversionService;
import com.xuande.spring.util.StringValueResolver;

/**
 * @author : xuande
 * @date : 2022-02-19 13:41
 **/
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);


    void destroySingletons();


    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    /**
     * Resolve the given embedded value, e.g. an annotation attribute.
     * @param value the value to resolve
     * @return the resolved value (may be the original value as-is)
     * @since 3.0
     */
    String resolveEmbeddedValue(String value);


    void setConversionService(ConversionService service);


    ConversionService getConversionService();
}
