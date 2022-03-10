package com.xuande.spring.core.convert.converter;

/**
 * @author : xuande
 * @date : 2022-03-10 20:33
 **/
public interface Converter<S, T> {

    /**
     * 将source对象转换为T
     * @param source
     * @return
     */
    T convert(S source);
}
