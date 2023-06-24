package com.driver;

import org.springframework.stereotype.Repository;

import javax.servlet.http.Part;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepsitory {
    // map to store order with orderid
    HashMap<String,Order> orderDb= new HashMap<>();

     //map to store partner with partner id
    HashMap<String,DeliveryPartner> partnerDb= new HashMap<>();

    //map to assign orderid to partner id
    HashMap<String,String> orderPartnerDb = new HashMap<>();

//assigning list of orders to partener id
    HashMap<String, List<String>> PartnerToOrder= new HashMap<>();


    public void addOrder(Order order) {
        String orderId= order.getId();
        orderDb.put(orderId,order);
    }

    public void addPartnerId(String partnerId) {
        DeliveryPartner partner= new DeliveryPartner(partnerId);
        partnerDb.put(partnerId,partner);
    }

    public void orderToPartner(String orderId, String partnerId) {
        if(partnerDb.containsKey(partnerId) && orderDb.containsKey(orderId)) {
            orderPartnerDb.put(partnerId, orderId);
        }

            //if i have no of many orders
            List<String> OrdersIDs = new ArrayList<>();

        // assigning orders to partners
        if (PartnerToOrder.containsKey(partnerId)) {
            OrdersIDs=PartnerToOrder.get(partnerId);
        }

        //assginnig the list of orders to partner
        OrdersIDs.add(orderId);
        PartnerToOrder.put(partnerId,OrdersIDs);

        DeliveryPartner deliveryPartner= new DeliveryPartner(partnerId);
        deliveryPartner.setNumberOfOrders(OrdersIDs.size());
        }


    public Order getOrderId(String orderId) {
        return orderDb.get(orderId);
    }

    public DeliveryPartner getPartner(String partnerId) {
        return partnerDb.get(partnerId);
    }
    public Integer getAllOrderOfPartner(String partnerId) {
        int count= PartnerToOrder.get(partnerId).size();
        return count;
    }

    public List<String> ordersByPartner(String partnerId) {
       return PartnerToOrder.get(partnerId);
    }

    public List<String> getAllOrder() {
        List<String> allOrder = new ArrayList<>();

        for(String orders:orderDb.keySet()){
            allOrder.add(orders);
        }
        return allOrder;
    }

    public Integer countUnassigned() {
        int totalOrders = orderDb.size();
        int assignedOrders = 0;

        for (List<String> orders : PartnerToOrder.values()) {
            assignedOrders += orders.size();
        }

        return totalOrders - assignedOrders;
    }

    public void deletePartnerID(String partnerId) {

   // getting all the orders assigned to the partner
        List<String>orders= PartnerToOrder.get(partnerId);
        PartnerToOrder.remove(partnerId);

        partnerDb.remove(partnerId);
        //removing all orders assgined to partner while iterating in orders list
        for (String order:orders)
        {
            orderPartnerDb.remove(order);
        }
    }

    public void deleteOrderID(String orderId) {

       orderPartnerDb.remove(orderId);


       if(orderPartnerDb.containsKey(orderId)){
           String partnerId= orderPartnerDb.get(orderId);
           orderPartnerDb.remove(orderId);
           PartnerToOrder.get(partnerId).remove(orderId);
           //decrease the size of the number of orders
           DeliveryPartner deliveryPartner =new DeliveryPartner(partnerId);
           deliveryPartner.setNumberOfOrders(PartnerToOrder.get(partnerId).size());
       }

    }


    public Integer getAllOrdersAfterGivenTime(int newTime, String partnerId) {
        int count = 0;
        List<String> orderId = PartnerToOrder.get(partnerId);
// since orders are stored in Orderdb, we will get the time of delivery
        for (String Ids : orderId) {
            int Deliverytime = orderDb.get(Ids).getDeliveryTime();
            if (newTime < Deliverytime) {
                count++;
            }
        }
        return count;
    }


    public int getLastDeliveryTime(String partnerId) {
        int lastTime = 0;

        // Get the orders assigned to the partner
        List<String> orders = PartnerToOrder.get(partnerId);

        if (orders != null) {
            for (String orderId : orders) {
                int deliveryTime = orderDb.get(orderId).getDeliveryTime();
                lastTime = Math.max(lastTime, deliveryTime);
            }
        }

        return lastTime;
    }
}

