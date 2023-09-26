package org.kainos.ea.resources;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.kainos.ea.api.OrderService;
import org.kainos.ea.cli.Order;

import java.util.List;


@Path("/api")
public class OrderController {
    private OrderService orderService = new OrderService();
@GET
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
    public List<Order> getOrders(){
        return orderService.getAllOrders();
    }
}
