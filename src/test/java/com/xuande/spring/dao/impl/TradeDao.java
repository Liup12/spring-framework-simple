package com.xuande.spring.dao.impl;

import com.xuande.spring.dao.ITradeDao;
import com.xuande.spring.stereotype.Component;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/3/1  16:38:29
 */

public class TradeDao implements ITradeDao {
    @Override
    public String selectById(String tradeId) {
        return "小红书订单 = " + tradeId;
    }
}
