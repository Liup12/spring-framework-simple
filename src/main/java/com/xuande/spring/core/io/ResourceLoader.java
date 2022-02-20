package com.xuande.spring.core.io;

/**
 * @author : xuande
 * @date : 2022-02-19 14:10
 **/
public interface ResourceLoader {

    String CLASSPATH_URL_PREFIX = "classpath:";

    Resource getResource(String location);
}
