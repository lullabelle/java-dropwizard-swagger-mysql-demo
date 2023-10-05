package org.kainos.ea.api;

import com.google.common.cache.RemovalListener;
import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.OrderValidator;
import org.kainos.ea.db.Orderdao;

import java.sql.Date;
import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;



public class OrderService {
   private Orderdao orderdao = new Orderdao();
   private OrderValidator orderValidator = new OrderValidator();

   public List<Order> getAllOrders() throws FailedToGetOrdersException {
        try {
            List<Order> orderList = orderdao.getAllOrders();
            //Update your `OrderService` and `Order` classes to print out the `OrderID`, `CustomerID` and `OrderDate` of all orders
            /* orderList.stream().sorted(Comparator.comparing(Order::getOrderDate)).forEach(System.out::println);*/

            //Update your `OrderService` to only show orders from the last week
            orderList.stream().filter(order -> order.getOrderDate()
                            .before(Date.from(Instant.now().minus(Duration.ofDays(7)))))
                            .forEach(System.out::println);

            //Update your `OrderService` to only show orders from customer with `CustomerID` 1
            orderList.stream().filter(order -> order.getCustomerID() == 1)
                    .sorted(Comparator.comparing(Order::getOrderDate))
                    .forEach(System.out::println);

            //Update your `OrderService` to only show the most recent order
            // orderList.stream().filter(order -> order.getOrderDate().)

            System.out.println("most recent " + Collections.max(orderList));

            //Update your `OrderService` to only show the oldest order
            System.out.println("oldest " + Collections.min(orderList));

            //Update your `OrderService` to show the total count of all orders
            System.out.println("total number of orders: " + orderList.size());

            //Update your `OrderService` to show the customer ID with the most orders
            Map<Integer, Long> counts = orderList.stream()
                    .collect(Collectors.groupingBy(order -> order.getCustomerID(), Collectors.counting()));

            System.out.println("Most Orders: " + Collections.max(counts.entrySet(), Map.Entry.comparingByValue()).getKey());

            System.out.println("Least Orders: " + Collections.min(counts.entrySet(), Map.Entry.comparingByValue()).getKey());


            return orderList;
        }catch (SQLException e){
            throw new FailedToGetOrdersException();
        }
    }
    public Order getOrderById(int id) throws FailedToGetOrdersException, OrderDoesNotExistException {
        try {
            Order order = orderdao.getOrderById(id);
            if (order == null) {
                throw new OrderDoesNotExistException();
            }
            return order;
        } catch (SQLException e) {
            throw new FailedToGetOrdersException();
        }
    }
    public int createOrder (OrderRequest order) throws FailedToCreateOrderException, InvalidOrderException {
        try {
            String validation = orderValidator.isValidOrder(order);
            if(validation != null){
                throw new InvalidOrderException(validation);
            }

            int id = orderdao.createOrder(order);

            if (id == -1) {
                throw new FailedToCreateOrderException();
            }
            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateOrderException();
        }
    }

    public void updateOrder(int id, OrderRequest order)throws InvalidOrderException, OrderDoesNotExistException, FailedToUpdateOrderException {
        try{
            String validation = orderValidator.isValidOrder(order);

            if(validation != null){
                throw new InvalidOrderException(validation);
            }
            Order orderToUpdate = orderdao.getOrderById(id);

            if(orderToUpdate == null){
                throw new OrderDoesNotExistException();
            }
            orderdao.updateOrder(id, order);
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToUpdateOrderException();
        }
    }

    public void deleteOrder(int id) throws OrderDoesNotExistException, FailedToDeleteProductException{
        try{
            Order orderToDelete = orderdao.getOrderById(id);
            if(orderToDelete == null){
                throw new OrderDoesNotExistException();
            }
            orderdao.deleteOrder(id);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToDeleteOrderException();
        }
    }

}//end

