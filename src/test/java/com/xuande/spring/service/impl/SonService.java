package com.xuande.spring.service.impl;

import com.xuande.spring.beans.factory.annotation.Autowired;
import com.xuande.spring.service.IFatherService;
import com.xuande.spring.service.ISonService;
import com.xuande.spring.stereotype.Component;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/3/8  09:35:30
 */
@Component
public class SonService implements ISonService {

    @Autowired
    private IFatherService fatherService;


    @Override
    public String getSonName() {
        return "小头儿子";
    }
}
