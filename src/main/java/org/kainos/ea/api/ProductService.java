package org.kainos.ea.api;

import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.*;
import org.kainos.ea.core.ProductValidator;
import org.kainos.ea.db.Productdao;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    private Productdao productdao = new Productdao();
    private ProductValidator productValidator = new ProductValidator();

    public List<Product> getProducts() throws FailedToGetProductsException {
        List<Product> products = productdao.getAllProducts();
        System.out.println(products);
        return products;
    }

    public Product getProductById(int id) throws FailedToGetProductsException, ProductDoesNotExistException {
        try {
            Product product = productdao.getProductById(id);
            if (product == null) {
                throw new ProductDoesNotExistException();
            }
            return product;
        } catch (SQLException e) {
            throw new FailedToGetProductsException();
        }
    }

    public int createProduct (ProductRequest product) throws FailedToCreateProductException, InvalidProductException {
        try {
            String validation = productValidator.isValidProduct(product);
            if(validation != null){
                throw new InvalidProductException(validation);
            }

            int id = productdao.createProduct(product);

            if (id == -1) {
                throw new FailedToCreateProductException();
            }
            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToCreateProductException();
        }
    }

    public void updateProduct(int id, ProductRequest product)throws InvalidProductException, ProductDoesNotExistException, FailedToUpdateProductException {
        try{
                String validation = productValidator.isValidProduct(product);

                if(validation != null){
                    throw new InvalidProductException(validation);
                }
                Product productToUpdate = productdao.getProductById(id);

                if(productToUpdate == null){
                    throw new ProductDoesNotExistException();
                }
                productdao.updateProduct(id, product);
            } catch (SQLException e) {
                System.err.println(e.getMessage());

                throw new FailedToUpdateProductException();
        }

    }

    public void deleteProduct(int id) throws ProductDoesNotExistException, FailedToDeleteProductException{
        try{
            Product productToDelete = productdao.getProductById(id);
            if(productToDelete == null){
                throw new ProductDoesNotExistException();
            }
            productdao.deleteProduct(id);
        }catch (SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToDeleteProductException();
        }
    }

}
