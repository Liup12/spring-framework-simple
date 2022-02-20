package com.xuande.spring;

import com.alibaba.fastjson.JSON;
import com.xuande.spring.beans.BeansException;
import com.xuande.spring.beans.factory.DisposableBean;
import com.xuande.spring.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : xuande
 * @date : 2022-02-19 13:10
 **/
public class UserDao {

    private static Map<String, Object> paramMap = new HashMap<>();

    static {
        paramMap.put("name","张三");
        paramMap.put("age",18);
        paramMap.put("city","北京");
    }


    public void selectUserInfo(){
        System.out.println(JSON.toJSON(paramMap));
    }


    public void init(){
        System.out.println("userDao invoke init()");
    }

    public void destroy(){
        System.out.println("userDao invoke destroy()");
    }


}
