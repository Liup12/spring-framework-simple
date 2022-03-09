package com.xuande.spring.service.impl;

import com.xuande.spring.beans.factory.annotation.Autowired;
import com.xuande.spring.service.IFatherService;
import com.xuande.spring.service.ISonService;
import com.xuande.spring.stereotype.Component;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/3/8  09:37:23
 */
@Component
public class FatherService implements IFatherService {

    @Autowired
    private ISonService sonService;

    @Override
    public String getFatherName() {
        return "大头爸爸";
    }
}
