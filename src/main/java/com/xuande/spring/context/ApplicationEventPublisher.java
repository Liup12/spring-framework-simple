package com.xuande.spring.context;

/**
 * @author : xuande
 * @date : 2022-02-21 21:08
 **/
public interface ApplicationEventPublisher {

    /**
     * 发布事件
     * @param event
     */
    void publishEvent(ApplicationEvent event);
}
