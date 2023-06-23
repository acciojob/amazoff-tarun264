package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {


   OrderRepsitory repository= new OrderRepsitory();


    public void add(Order order) {
     repository.addOrder(order);
    }

    public void addPartner(String partnerId) {
     repository.addPartnerId(partnerId);
    }

    public void orderToPart(String orderId, String partnerId) {
     repository.orderToPartner(orderId,partnerId);
    }

    public Order getOrderID(String orderId) {
     return repository.getOrderId(orderId);
    }

    public DeliveryPartner getPartner(String partnerId) {
     return repository.getPartner(partnerId);
    }

    public Integer count(String partnerId) {
    return repository.getAllOrderOfPartner(partnerId);
    }


 public List<String> OrdersByPartner(String partnerId) {
     return repository.ordersByPartner(partnerId);
    }

 public List<String> getAllOrders() {
     return repository.getAllOrder();

 }

 public Integer countUnassigned() {
     return repository.countUnassigned();
 }

 public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {
    String Deliverytime[]=time.split(":");
    int newTime=(Integer.parseInt(Deliverytime[0])*60+ Integer.parseInt(Deliverytime[1]));

    return repository.getAllOrdersAfterGivenTime(newTime,partnerId);


 }

 public String getLastDeliveryTime(String partnerId) {
        int time= repository.getLastDeliveryTime(partnerId);
        String HH=Integer.toString(time/60);
        String MM=Integer.toString(time%60);

        if(HH.length()<2){
            HH='0'+HH;
        }
        if(MM.length()<2){
            MM='0'+MM;
        }
        return HH+':'+MM;

 }

 public void deletePartnerId(String partnerId) {
     repository.deletePartnerID(partnerId);
 }

 public void deleteOrderID(String orderId) {
     repository.deleteOrderID(orderId);
 }
}
