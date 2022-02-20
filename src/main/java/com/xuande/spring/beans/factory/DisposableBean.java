package com.xuande.spring.beans.factory;

/**
 * @author : xuande
 * @date : 2022-02-20 15:04
 **/
public interface DisposableBean {


    /**
     * bean销毁回调方法
     * @throws Exception
     */
    void destroy() throws Exception;
}
