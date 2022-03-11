package com.xuande.spring.core.convert.support;

import com.xuande.spring.core.convert.converter.ConverterRegistry;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/3/11  10:00:24
 */
public class DefaultConversionService extends GenericConversionService{

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry registry){
        registry.addConverterFactory(new StringToNumberConverterFactory());
    }
}
