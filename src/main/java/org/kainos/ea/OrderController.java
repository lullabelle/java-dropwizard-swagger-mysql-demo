package org.kainos.ea;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path("/api")
public class OrderController {
@GET
@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
    public String getOrders(){
    return "Replace this with a list of Orders";
    }
}
