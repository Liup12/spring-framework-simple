package com.xuande.spring.core.convert.converter;

/**
 * @author : xuande
 * @date : 2022-03-10 20:38
 **/
public interface ConverterRegistry {

    void addConverter(Converter<?, ?> converter);

    void addConverter(GenericConverter genericConverter);

    void addConverterFactory(ConverterFactory<?, ?> convertFactory);
}
