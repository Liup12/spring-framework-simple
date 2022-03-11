package com.xuande.spring.core.convert.support;

import com.xuande.spring.core.convert.converter.Converter;
import com.xuande.spring.core.convert.converter.ConverterFactory;
import com.xuande.spring.util.NumberUtils;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/3/11  10:02:10
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {
    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<>(targetType);
    }



    private static final class StringToNumber<T extends Number> implements Converter<String, T>{
        private final Class<T> targetType;

        private StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }


        @Override
        public T convert(String source) {
            if (source.isEmpty()){
                return null;
            }
            return NumberUtils.parseNumber(source, this.targetType);
        }
    }
}
