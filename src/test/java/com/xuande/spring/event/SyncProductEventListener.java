package com.xuande.spring.event;

import com.xuande.spring.context.ApplicationListener;

/**
 * @author : xuande
 * @date : 2022-02-21 22:15
 **/
public class SyncProductEventListener implements ApplicationListener<SyncProductEvent> {
    @Override
    public void onApplicationEvent(SyncProductEvent event) {
        System.out.println(String.format("同步到商品条码【%s】, 商品名称【%s】", event.getItemCode(), event.getItemName()));
    }
}
