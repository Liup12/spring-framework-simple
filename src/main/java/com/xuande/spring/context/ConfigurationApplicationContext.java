package com.xuande.spring.context;

import com.xuande.spring.beans.BeansException;

/**
 * @author : xuande
 * @date : 2022-02-19 16:46
 **/
public interface ConfigurationApplicationContext extends ApplicationContext{

    /**
     * 刷新应用程序上下文
     */
    void refresh() throws BeansException;

    void registerShutdownHook();

    void close();
}
