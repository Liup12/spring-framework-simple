package com.xuande.spring.context.event;

import com.xuande.spring.context.ApplicationEvent;
import com.xuande.spring.context.ApplicationListener;

/**
 * @author : xuande
 * @date : 2022-02-21 21:18
 **/
public interface ApplicationEventMulticaster {

    /**
     * 添加监听者
     * @param listener
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * 删除监听者
     * @param listener
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * 通知监听者
     * @param event
     */
    void multicastEvent(ApplicationEvent event);

}
