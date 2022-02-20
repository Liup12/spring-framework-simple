package com.xuande.spring.beans;

/**
 * @author : xuande
 * @date : 2022-02-19 11:22
 **/
public class PropertyValue {

    private final String name;

    private final Object value;


    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }


    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
