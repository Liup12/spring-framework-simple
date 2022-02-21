package com.xuande.spring.service;

import com.xuande.spring.dao.IProductDao;

/**
 * @author : xuande
 * @date : 2022-02-21 20:25
 **/
public class ProductService {

    private String itemCode;

    private String itemName;

    private IProductDao productDao;

    public String queryPrice(String id) {
        return productDao.queryPrice(id) + "," + itemCode + "," + itemName;
    }

}
