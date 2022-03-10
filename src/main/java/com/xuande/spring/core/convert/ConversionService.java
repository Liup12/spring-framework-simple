package com.xuande.spring.core.convert;

/**
 * @author : xuande
 * @date : 2022-03-10 20:47
 **/
public interface ConversionService {

    /**
     * 判断是否可转换
     * @param sourceType
     * @param targetType
     * @return
     */
    boolean canConvert(Class<?> sourceType, Class<?> targetType);

    /**
     * 类型转换
     * @param source
     * @param targetType
     * @param <T>
     * @return
     */
    <T> T convert(Object source, Class<?> targetType);
}
