package com.xuande.spring.event;

import com.xuande.spring.context.ApplicationListener;
import com.xuande.spring.context.event.ContextClosedEvent;

/**
 * @author : xuande
 * @date : 2022-02-21 22:03
 **/
public class ContextClosedEventListener implements ApplicationListener<ContextClosedEvent> {

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        System.out.println("容器关闭了  " + this.getClass().getName());
    }
}
