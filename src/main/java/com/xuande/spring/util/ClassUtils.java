package com.xuande.spring.util;

/**
 * @author : xuande
 * @date : 2022-02-19 14:24
 **/
public class ClassUtils {

    public static ClassLoader getDefaultClassLoader(){
        ClassLoader cl = null;
        try {
            cl = Thread.currentThread().getContextClassLoader();
        }
        catch (Throwable ex) {
            // Cannot access thread context ClassLoader - falling back to system class loader...
        }
        if (cl == null) {
            // No thread context class loader -> use class loader of this class.
            cl = ClassUtils.class.getClassLoader();
        }
        return cl;
    }


    public static boolean isCglibProxyClass(Class<?> clazz){
        return clazz != null && isCglibClassName(clazz.getName());
    }

    public static boolean isCglibClassName(String className){
        return className != null && className.contains("$$");
    }
}
