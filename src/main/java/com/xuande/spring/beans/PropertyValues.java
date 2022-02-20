package com.xuande.spring.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : xuande
 * @date : 2022-02-19 11:23
 **/
public class PropertyValues {

    private final List<PropertyValue> propertyValueList = new ArrayList<>();

    public void addPropertyValue(PropertyValue propertyValue){
        propertyValueList.add(propertyValue);
    }

    public PropertyValue[] getPropertyValueList() {
        return propertyValueList.toArray(new PropertyValue[0]);
    }

    public PropertyValue getPropertyValue(String propertyName){

        for (PropertyValue propertyValue : propertyValueList) {
            if (propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }

        return null;
    }
}
