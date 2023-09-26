package org.kainos.ea.cli;

public class Product {
    private int productID;
    private String name;
    private double price;

    //constructor
    public Product(int productID, String name, double price) {
        setProductID (productID);
        setName(name);
        setPrice(price);
    }

    //Getters & Setters
    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        price = price;
    }





}
