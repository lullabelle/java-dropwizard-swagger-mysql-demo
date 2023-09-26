package org.kainos.ea.api;

import org.kainos.ea.cli.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {

    public List<Product> getAllProducts(){
        List<Product> productList = new ArrayList<>();
        Product product11 = new Product(11,"Nails", 0.12);
        Product product12 = new Product(12,"Glue", 2.99);
        Product product13 = new Product(13,"Spanner", 5.50);

        productList.add(product11);
        productList.add(product12);
        productList.add(product13);

        return productList;
    }
}
