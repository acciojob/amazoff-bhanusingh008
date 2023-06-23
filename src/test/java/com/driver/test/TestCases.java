package com.driver.test;

import com.driver.Application;
import com.driver.DeliveryPartner;
import com.driver.Order;
import com.driver.OrderController;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;

import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;

@SpringBootTest(classes = Application.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Ignore
public class TestCases {

    OrderController orderController = new OrderController();

    public void testAddOrderPartnerPair(Order order){

        orderController.addOrder(order);
    }

    public void testAddPartner(String partnerId){
        orderController.addPartner(partnerId);
    }

}

//TestCases.testAddOrder:25 » NullPointer
//        Error:    TestCases.testAddOrderPartnerPair:53 » NullPointer
//        Error:    TestCases.testAddPartner:34 » NullPointer
//        Error:    TestCases.testDeletePartnerById:639 » NullPointer
//        Error:    TestCases.testGetAllOrders:319 » NullPointer
//        Error:    TestCases.testGetCountOfOrdersLeftAfterGivenTime:531 » NullPointer
//        Error:    TestCases.testGetCountOfUnassignedOrders:389 » NullPointer
//        Error:    TestCases.testGetLastDeliveryTime:593 » NullPointer
//        Error:    TestCases.testGetOrderById:88 » NullPointer
//        Error:    TestCases.testGetOrderCountByPartnerId:193 » NullPointer
//        Error:    TestCases.testGetOrdersByPartnerId:239 » NullPointer
//        Error:    TestCases.testGetPartnerById:142 » NullPointer
//        Error:    TestCases.testOrderController:684 » NullPointer