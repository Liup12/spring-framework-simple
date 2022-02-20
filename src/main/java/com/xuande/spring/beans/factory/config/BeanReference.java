package com.xuande.spring.beans.factory.config;

/**
 * @author : xuande
 * @date : 2022-02-19 12:54
 **/
public class BeanReference {

    private final String name;


    public BeanReference(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
