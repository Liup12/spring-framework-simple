package com.xuande.spring;

import com.alibaba.fastjson.JSON;
import com.xuande.spring.core.convert.converter.Converter;
import com.xuande.spring.core.convert.converter.GenericConverter;
import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : xuande
 * @date : 2022-03-10 21:08
 **/
public class TypeTest {


    @Test
    public void testGetGenericInterfaces(){
        A a = new A();
        Type[] types = a.getClass().getGenericInterfaces();

        ParameterizedType parameterizedType = (ParameterizedType) types[1];

        Type[] typeArguments = parameterizedType.getActualTypeArguments();
        Class sourceType = (Class) typeArguments[0];
        Class targetType = (Class) typeArguments[1];
        System.out.println(sourceType.getName());
        System.out.println(targetType.getName());
    }


    @Test
    public void testGetHierarchyClass(){
        A a = new A();
        E e = new E();
        List<Class<?>> classHierarchy = getClassHierarchy(e.getClass());


        System.out.println(JSON.toJSON(classHierarchy));
    }

    private List<Class<?>> getClassHierarchy(Class<?> clazz) {
        List<Class<?>> hierarchy = new ArrayList<>();
        while (clazz != null) {
            hierarchy.add(clazz);
            clazz = clazz.getSuperclass();
        }
        return hierarchy;
    }

    private interface B<S, T>{

    }

    private interface C<S, T>{

    }

    private class A implements B<String, Integer>, C<Number, Date>{

    }


    private class D extends A{ }
    private class E extends D{ }
    private final class F extends E{ }
}

