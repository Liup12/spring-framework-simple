package com.xuande.spring.event;

import com.xuande.spring.context.ApplicationListener;
import com.xuande.spring.context.event.ContextRefreshedEvent;

/**
 * @author : xuande
 * @date : 2022-02-21 22:04
 **/
public class ContextRefreshedEventListener implements ApplicationListener<ContextRefreshedEvent> {


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        System.out.println("容器刷新完成  " + this.getClass().getName());
    }
}
