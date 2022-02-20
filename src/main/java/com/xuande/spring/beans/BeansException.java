package com.xuande.spring.beans;

/**
 * @author : xuande
 * @date : 2022-02-19 10:17
 **/
public class BeansException extends RuntimeException{

    public BeansException(String message) {
        super(message);
    }


    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
