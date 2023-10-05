package org.kainos.ea.resources;

import io.swagger.annotations.Api;
import org.kainos.ea.api.OrderService;
import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;


@Api("Engineering Academy Dropwizard Order API")
@Path("/api")
public class OrderController {
    private OrderService orderService = new OrderService();
@GET
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
    public Response getOrders() throws SQLException {
    try {
        return Response.ok(orderService.getAllOrders()).build();
    } catch (FailedToGetOrdersException e) {
        System.err.println(e.getMessage());
        return Response.serverError().build();
    }
}
    @GET
    @Path("/orders/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("id") int orderId) {
        try {
            return Response.ok(orderService.getOrderById(orderId)).build();

        } catch (OrderDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch (FailedToGetOrdersException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();
        }
    }

    @POST
    @Path("/orders/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(OrderRequest order){
        try{
            return Response.ok(orderService.createOrder(order)).build();
        } catch (FailedToCreateOrderException e) {

            System.err.println(e.getMessage());
            return Response.serverError().build();

        } catch (InvalidOrderException e) {

            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
    @PUT
    @Path("/orders/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(@PathParam("id") int id, OrderRequest order){
        try{
            orderService.updateOrder(id, order);
            return Response.ok().build();
        } catch (InvalidOrderException | OrderDoesNotExistException e) {
            System.err.println(e.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();

        } catch (FailedToUpdateOrderException e) {
            System.err.println(e.getMessage());
            return Response.serverError().build();

        }
    }
}
