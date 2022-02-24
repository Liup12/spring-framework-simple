package com.xuande.spring.service.impl;

import com.xuande.spring.service.IShopService;

/**
 * @author : xuande
 * @date : 2022-02-24 21:39
 **/
public class ShopService implements IShopService {



    @Override
    public String queryShopInfo() {
        return "1001, 毛戈平官方旗舰店";
    }
}
