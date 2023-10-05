package org.kainos.ea.cli;

public class Product implements Comparable<Product>{
    private int productID;
    private String name;

    private String Description;
    private double price;

    //constructor


    public Product(int productID, String name, String description, double price) {
        this.productID = productID;
        this.name = name;
        Description = description;
        this.price = price;
    }

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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public int compareTo(Product product) {
        return Double.compare(this.getPrice(), product.getPrice());
    }

    @Override
    public String toString(){
        return "Product Name: " + this.getName() + ", Product Price: " + this.getPrice();
    }
}
