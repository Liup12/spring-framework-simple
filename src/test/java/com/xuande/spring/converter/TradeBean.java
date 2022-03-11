package com.xuande.spring.converter;

import java.time.LocalDate;

/**
 * @author xuande (xuande@dajiaok.com)
 * @date 2022/3/11  14:53:31
 */
public class TradeBean {

    private String itemCode;

    private Integer weight;

    private LocalDate buyDay;

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public LocalDate getBuyDay() {
        return buyDay;
    }

    public void setBuyDay(LocalDate buyDay) {
        this.buyDay = buyDay;
    }

    @Override
    public String toString() {
        return "TradeBean{" +
                "itemCode='" + itemCode + '\'' +
                ", weight=" + weight +
                ", buyDay=" + buyDay +
                '}';
    }
}
