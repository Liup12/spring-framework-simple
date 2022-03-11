package com.xuande.spring.converter;

import com.xuande.spring.beans.factory.FactoryBean;
import com.xuande.spring.core.convert.converter.Converter;

import java.util.HashSet;
import java.util.Set;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/3/11  14:43:13
 */
public class ConverterFactoryBean implements FactoryBean<Set<?>> {


    @Override
    public Set<?> getObject() throws Exception {
        Set<Converter> set = new HashSet<>();
        set.add(new StringToIntegerConverter());
        set.add(new StringToLocalDateConverter("yyyy-MM-dd"));
        return set;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
