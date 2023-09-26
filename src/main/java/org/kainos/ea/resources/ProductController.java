package org.kainos.ea.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.kainos.ea.api.ProductService;
import org.kainos.ea.cli.Product;

import java.util.List;

@Path("/api")
public class ProductController {
        private ProductService productService = new ProductService();
    @GET
    @Path("/products")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getProducts(){
        return productService.getAllProducts();
    }
}
