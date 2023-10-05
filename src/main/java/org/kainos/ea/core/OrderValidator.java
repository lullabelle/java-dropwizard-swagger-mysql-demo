package org.kainos.ea.core;

import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.cli.ProductRequest;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Date;

public class OrderValidator {


    public String isValidOrder(OrderRequest order){
        Instant orderDate = order.getOrderDate().toInstant();
        Instant aYearAgo = ZonedDateTime.now().minusYears(1).toInstant();
        if(order.getCustomerID() < 1){
            return "Customer does not exist in database";
        }
        if(orderDate.isBefore(aYearAgo)){
            return "Description greater than 500 characters";
        }

        return null;
    }
}
