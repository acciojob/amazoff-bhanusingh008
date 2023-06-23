package com.driver;

import io.swagger.models.auth.In;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class OrderRepository {

    HashMap<String, Order> orderDB = new HashMap<>();

    HashMap<String, DeliveryPartner> partnerDB = new HashMap<>();

    HashMap<String, String> orderPartnerDB = new HashMap<>();

    HashMap<String, List<String>> partnerOrderDB = new HashMap<>();

    public void addOrder(Order order) {
        orderDB.put(order.getId(), order);
    }

    public void addPartner(String partnerId) {
        partnerDB.put(partnerId, new DeliveryPartner(partnerId));
    }

    public void addOrderPartnerPair(String orderId, String partnerId) {
        if(orderDB.containsKey(orderId) && partnerDB.containsKey(partnerId)){
            orderPartnerDB.put(orderId, partnerId);

            List<String> curr_orders = new ArrayList<>();

            if(partnerOrderDB.containsKey(partnerId)){
                curr_orders = partnerOrderDB.get(partnerId);
            }

            curr_orders.add(orderId);

            partnerOrderDB.put(partnerId, curr_orders);

            // increase the number of orders for partner.

            DeliveryPartner deliveryPartner = partnerDB.get(partnerId);

            deliveryPartner.setNumberOfOrders(curr_orders.size());
        }
    }

    public Order getOrderById(String orderId) {
        return orderDB.get(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId) {
        return partnerDB.get(partnerId);
    }

    public Integer getOrderCountByPartnerId(String partnerId) {
        return partnerOrderDB.get(partnerId).size();
    }

    public List<String> getOrdersByPartnerId(String partnerId) {
        return partnerOrderDB.get(partnerId);
    }

    public List<String> getAllOrders() {
        List<String> orders = new ArrayList<>();

        for(String order : orderDB.keySet()){
            orders.add(order);
        }
        return orders;
    }

    public Integer getCountOfUnassignedOrders() {
        return orderDB.size() - orderPartnerDB.size();
    }

    public Integer getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId) {

        String tem[] = time.split(":");

        int hour = Integer.parseInt(tem[0]);
        int min = Integer.parseInt(tem[1]);

        int curr_time = 60*hour+min;

        List<String> orders = partnerOrderDB.get(partnerId);
        int count = 0;

        for(String order : orders){
            Order order1 = orderDB.get(order);

            if(order1.getDeliveryTime() > curr_time){
                count++;
            }
        }
        return count;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId) {

        List<String> orders = partnerOrderDB.get(partnerId);

        Order last = null;

        int last_time = 0;

        for(String order : orders){

            Order od = orderDB.get(order);

            if(od.getDeliveryTime() > last_time){
                last = od;
                last_time = od.getDeliveryTime();
            }
        }

        int hour = last_time/60;
        int min = last_time%60;

        String hourPart = "";
        hourPart+=hour;
        String minPart = "";
        minPart+=min;

        if(hour < 10){
            hourPart = "0" + hourPart;
        }

        if(min < 10){
            minPart = "0" + minPart;
        }

        return hourPart+":"+minPart;
    }

    public void deletePartnerById(String partnerId) {

        partnerDB.remove(partnerId);

        List<String> orders = partnerOrderDB.get(partnerId);

        partnerOrderDB.remove(partnerId);

        for(String order : orders){

            orderPartnerDB.remove(order);
        }
    }

    public void deleteOrderById(String orderId) {

        orderDB.remove(orderId);

        String partnerId = orderPartnerDB.get(orderId);

        orderPartnerDB.remove(orderId);

        List<String> curr_orders = partnerOrderDB.get(partnerId);

        curr_orders.remove(orderId);
    }
}
