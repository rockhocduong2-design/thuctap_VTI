package com.example.model;

public class Product {
    private int productID;
    private String name;
    private int order;
    private boolean isActive;

    public Product() {
    }

    public Product(int productID, String name, int order, boolean isActive) {
        this.productID = productID;
        this.name = name;
        this.order = order;
        this.isActive = isActive;
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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Product [productID=" + productID + ", name=" + name + ", order=" + order + ", isActive=" + isActive
                + "]";
    }

}
