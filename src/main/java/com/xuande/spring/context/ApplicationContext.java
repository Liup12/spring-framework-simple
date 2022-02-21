package com.xuande.spring.context;

import com.xuande.spring.beans.factory.HierarchicalBeanFactory;
import com.xuande.spring.beans.factory.ListableBeanFactory;
import com.xuande.spring.core.io.ResourceLoader;

/**
 * @author : xuande
 * @date : 2022-02-19 16:45
 **/
public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader, ApplicationEventPublisher  {


}
