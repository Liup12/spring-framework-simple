package com.xuande.spring.context.support;

import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.factory.FactoryBean;
import com.xuande.spring.beans.factory.InitializingBean;
import com.xuande.spring.core.convert.ConversionService;
import com.xuande.spring.core.convert.converter.Converter;
import com.xuande.spring.core.convert.converter.ConverterFactory;
import com.xuande.spring.core.convert.converter.ConverterRegistry;
import com.xuande.spring.core.convert.converter.GenericConverter;
import com.xuande.spring.core.convert.support.DefaultConversionService;
import com.xuande.spring.core.convert.support.GenericConversionService;

import java.util.Set;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/3/11  13:41:06
 */
public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {



    private Set<?> converters;

    private GenericConversionService conversionService;


    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    @Override
    public Class<?> getObjectType() {
        return conversionService.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws BeansException {
        this.conversionService = new DefaultConversionService();
        registerConverters(this.converters, conversionService);
    }


    public void registerConverters(Set<?> converters, ConverterRegistry converterRegistry){
        if (converters != null){
            for (Object converter : converters) {
                if (converter instanceof Converter){
                    converterRegistry.addConverter((Converter<?, ?>) converter);
                }else if (converter instanceof GenericConverter){
                    converterRegistry.addConverter(((GenericConverter) converter));
                }else if (converter instanceof ConverterFactory){
                    converterRegistry.addConverterFactory(((ConverterFactory<?, ?>) converter));
                }else {
                    throw new IllegalArgumentException("Each converter object must implement one of the " +
                            "Converter, ConverterFactory, or GenericConverter interfaces");
                }
            }
        }
    }

    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }
}
