package com.xuande.spring.event;

import com.xuande.spring.context.ApplicationEvent;

/**
 * @author : xuande
 * @date : 2022-02-21 22:14
 **/
public class SyncProductEvent extends ApplicationEvent {

    private String itemCode;

    private String itemName;


    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public SyncProductEvent(Object source, String itemCode, String itemName) {
        super(source);
        this.itemCode = itemCode;
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
