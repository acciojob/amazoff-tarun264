package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class Order {


    private String id;
    private int deliveryTime;

    public void setId(String id) {
        this.id = id;
    }

    public void setDeliveryTime(int deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Order(String id, String deliveryTime) {
        this.id = id;
        int hours = Integer.parseInt(deliveryTime.substring(0, 2));
        int minutes = Integer.parseInt(deliveryTime.substring(3));
        int delivTime = hours * 60 + minutes;
        this.deliveryTime = delivTime;

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {
        return deliveryTime;
    }


}