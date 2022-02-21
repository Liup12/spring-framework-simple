package com.xuande.spring.context;

import java.util.EventListener;

/**
 * @author : xuande
 * @date : 2022-02-21 21:10
 **/
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {

    void onApplicationEvent(E event);
}
