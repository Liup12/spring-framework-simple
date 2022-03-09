package com.xuande.spring.service.impl;

import com.xuande.spring.beans.factory.annotation.Value;
import com.xuande.spring.service.IShopService;

/**
 * @author : xuande
 * @date : 2022-02-24 21:39
 **/
public class ShopService implements IShopService {


    @Value("${token}")
    private String token;

    @Override
    public String queryShopInfo() {
        return "1001, 毛戈平官方旗舰店";
    }

    @Override
    public String queryShopToken() {
        return token;
    }

}
