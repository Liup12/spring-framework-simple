package com.xuande.spring.core.convert.support;

import com.xuande.spring.core.convert.ConversionService;
import com.xuande.spring.core.convert.converter.Converter;
import com.xuande.spring.core.convert.converter.ConverterFactory;
import com.xuande.spring.core.convert.converter.ConverterRegistry;
import com.xuande.spring.core.convert.converter.GenericConverter;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;

/**
 * @author : xuande
 * @date : 2022-03-10 20:49
 **/
public class GenericConversionService implements ConverterRegistry, ConversionService {

    private Map<GenericConverter.ConvertiblePair, GenericConverter> converters = new HashMap<>();

    @Override
    public boolean canConvert(Class<?> sourceType, Class<?> targetType) {
        GenericConverter converter = getConverter(sourceType, targetType);
        return converter != null;
    }

    @Override
    public <T> T convert(Object source, Class<?> targetType) {
        Class<?> sourceType = source.getClass();

        GenericConverter converter = getConverter(sourceType, targetType);
        return (T) converter.convert(source, sourceType, targetType);
    }

    @Override
    public void addConverter(Converter<?, ?> converter) {
        GenericConverter.ConvertiblePair convertiblePair = getRequiredTypeInfo(converter);
        ConverterAdapter converterAdapter = new ConverterAdapter(convertiblePair, converter);
        for (GenericConverter.ConvertiblePair convertibleType : converterAdapter.getConvertibleTypes()) {
            converters.put(convertibleType, converterAdapter);
        }

    }

    @Override
    public void addConverter(GenericConverter genericConverter) {
        for (GenericConverter.ConvertiblePair convertiblePair : genericConverter.getConvertibleTypes()) {
            converters.put(convertiblePair, genericConverter);
        }
    }

    @Override
    public void addConverterFactory(ConverterFactory<?, ?> convertFactory) {
        GenericConverter.ConvertiblePair convertiblePair = getRequiredTypeInfo(convertFactory);
        ConverterFactoryAdapter factoryAdapter = new ConverterFactoryAdapter(convertiblePair, convertFactory);
        for (GenericConverter.ConvertiblePair convertibleType : factoryAdapter.getConvertibleTypes()) {
            converters.put(convertibleType, factoryAdapter);
        }
    }


    /**
     * 获取对象中需要的类型
     *
     * 例如Converter<String,Integer>
     *
     * return : ConvertiblePair 中 sourceType = String.class、 targetType = Integer.class
     * @param object
     * @return
     */
    protected GenericConverter.ConvertiblePair getRequiredTypeInfo(Object object){
        // 返回该类实现的所有接口数组
        Type[] types = object.getClass().getGenericInterfaces();
        // 取第一个接口
        ParameterizedType type = (ParameterizedType) types[0];

        //获取接口泛型类型数组
        Type[] arguments = type.getActualTypeArguments();
        Class sourceType = (Class) arguments[0];
        Class targetType = (Class) arguments[0];
        return new GenericConverter.ConvertiblePair(targetType, sourceType);
    }

    /**
     * 获取Class的层次结构
     * @param clazz
     * @return
     */
    protected List<Class<?>> getClassHierarchy(Class<?> clazz){
        List<Class<?>> hierarchyClass = new ArrayList<>();
        while (clazz != null){
            hierarchyClass.add(clazz);
            clazz = clazz.getSuperclass();
        }
        return hierarchyClass;
    }

    /**
     * 获取转换器
     * @param sourceType
     * @param targetType
     * @return
     */
    protected GenericConverter getConverter(Class<?> sourceType, Class<?> targetType){
        List<Class<?>> sourceCandidates = getClassHierarchy(sourceType);
        List<Class<?>> targetCandidates = getClassHierarchy(targetType);
        for (Class<?> sourceCandidate : sourceCandidates) {
            for (Class<?> targetCandidate : targetCandidates) {
                GenericConverter.ConvertiblePair convertiblePair = new GenericConverter.ConvertiblePair(sourceCandidate, targetCandidate);
                GenericConverter genericConverter = converters.get(convertiblePair);
                if (genericConverter != null){
                    return genericConverter;
                }
            }
        }
        return null;
    }


    private final static class ConverterAdapter implements GenericConverter{


        private final ConvertiblePair typeInfo;
        private final Converter<Object, Object> converter;

        private ConverterAdapter(ConvertiblePair typeInfo, Converter<?, ?> converter) {
            this.typeInfo = typeInfo;
            this.converter = (Converter<Object, Object>) converter;
        }


        @Override
        public Set<ConvertiblePair> getConvertibleTypes() {
            return Collections.singleton(typeInfo);
        }

        @Override
        public Object convert(Object source, Class<?> sourceType, Class<?> targetType) {
            return converter.convert(source);
        }
    }


    private final class ConverterFactoryAdapter implements GenericConverter{

        private final ConvertiblePair typeInfo;
        private final ConverterFactory<Object, Object>  converterFactory;

        private ConverterFactoryAdapter(ConvertiblePair typeInfo, ConverterFactory<?, ?> converterFactory) {
            this.typeInfo = typeInfo;
            this.converterFactory = (ConverterFactory<Object, Object>) converterFactory;
        }


        @Override
        public Set<ConvertiblePair> getConvertibleTypes() {
            return Collections.singleton(typeInfo);
        }

        @Override
        public Object convert(Object source, Class<?> sourceType, Class<?> targetType) {
            return converterFactory.getConverter(targetType).convert(source);
        }
    }
}
