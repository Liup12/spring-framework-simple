package com.xuande.spring.core.convert.converter;

import cn.hutool.core.lang.Assert;

import java.util.Set;

/**
 * @author : xuande
 * @date : 2022-03-10 20:41
 **/
public interface GenericConverter {


    Set<ConvertiblePair> getConvertibleTypes();


    Object convert(Object source, Class<?> sourceType, Class<?> targetType);



    final class ConvertiblePair {
        public final Class<?> targetType;
        public final Class<?> sourceType;


        public ConvertiblePair(Class<?> targetType, Class<?> sourceType) {
            Assert.notNull(sourceType, "Source type must not be null");
            Assert.notNull(targetType, "Target type must not be null");
            this.targetType = targetType;
            this.sourceType = sourceType;
        }


        public Class<?> getTargetType() {
            return targetType;
        }

        public Class<?> getSourceType() {
            return sourceType;
        }
    }
}
