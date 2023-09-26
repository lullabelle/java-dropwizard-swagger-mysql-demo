package org.kainos.ea.api;

import org.kainos.ea.cli.Order;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderService {
    public List<Order> getAllOrders(){
        List<Order> orderList = new ArrayList<>();
        Order order1 = new Order (2, 2, new Date());
        Order order2 = new Order (2, 2, new Date());

        orderList.add(order1);
        orderList.add(order2);

        return orderList;
    }
}
