package com.xuande.spring.context.event;

import com.xuande.spring.context.ApplicationContext;
import com.xuande.spring.context.ApplicationEvent;

/**
 * @author : xuande
 * @date : 2022-02-21 21:30
 **/
public class ApplicationContextEvent extends ApplicationEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext(){
        return (ApplicationContext) source;
    }
}
