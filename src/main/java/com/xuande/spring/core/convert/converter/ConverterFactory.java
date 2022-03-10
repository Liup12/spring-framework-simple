package com.xuande.spring.core.convert.converter;

/**
 * 转换器工厂
 * @author : xuande
 * @date : 2022-03-10 20:35
 **/
public interface ConverterFactory<S, R> {

    /**
     * 获取转换器
     * @param targetType
     * @param <T>
     * @return
     */
    <T extends R> Converter<S, T> getConverter(Class<T> targetType);

}
