package com.xuande.spring.beans.factory.support;

import com.xuande.spring.core.io.Resource;
import com.xuande.spring.core.io.ResourceLoader;

/**
 * @author : xuande
 * @date : 2022-02-19 14:31
 **/
public interface BeanDefinitionReader {


    BeanDefinitionRegistry getRegister();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource);

    void loadBeanDefinitions(Resource ...resource);

    void loadBeanDefinitions(String location);

    void loadBeanDefinitions(String ...location);
}
