package com.xuande.spring.context;

import java.util.EventObject;

/**
 * @author : xuande
 * @date : 2022-02-21 21:06
 **/
public abstract class ApplicationEvent extends EventObject {


    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
