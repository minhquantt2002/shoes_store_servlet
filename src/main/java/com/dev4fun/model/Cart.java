package com.dev4fun.model;

public class Cart {
    private Product product;
    private int size;
    private int quantity;

    public Cart(Product product, int size, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
