package com.xuande.spring;

import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.factory.DisposableBean;
import com.xuande.spring.beans.factory.InitializingBean;

/**
 * @author : xuande
 * @date : 2022-02-19 10:49
 **/
public class UserService  implements InitializingBean, DisposableBean {

    private String name;

    private UserDao userDao;


    public void sayHello(){
        System.out.println("hello spring-framework " + name);
    }


    public void selectUser(){
        userDao.selectUserInfo();
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("UserService invoke destroy()");
    }

    @Override
    public void afterPropertiesSet() throws BeansException {
        System.out.println("UserService invoke init()");
    }
}
