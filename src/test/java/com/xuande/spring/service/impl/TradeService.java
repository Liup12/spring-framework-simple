package com.xuande.spring.service.impl;

import com.xuande.spring.beans.factory.annotation.Autowired;
import com.xuande.spring.beans.factory.annotation.Value;
import com.xuande.spring.dao.ITradeDao;
import com.xuande.spring.service.ITradeService;
import com.xuande.spring.stereotype.Component;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/3/1  10:58:02
 */
@Component("tradeService")
public class TradeService implements ITradeService {

    @Value("${token}")
    private String token;

    @Autowired
    private ITradeDao tradeDao;

    @Override
    public String selectByTradeId(String tradeId) {
        return tradeDao.selectById(tradeId);
    }

    @Override
    public String toString() {
        return "TradeService#token = { " + token + " }";
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
