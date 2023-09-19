package org.kainos.ea;

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
