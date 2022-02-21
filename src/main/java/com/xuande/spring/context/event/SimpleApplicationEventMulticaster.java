package com.xuande.spring.context.event;

import com.xuande.spring.beans.factory.BeanFactory;
import com.xuande.spring.context.ApplicationEvent;
import com.xuande.spring.context.ApplicationListener;

/**
 * @author : xuande
 * @date : 2022-02-21 21:32
 **/
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster{


    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
