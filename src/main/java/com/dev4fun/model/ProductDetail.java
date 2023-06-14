package com.dev4fun.model;

public class ProductDetail {
    private int id;
    private int productId;
    private String size;
    private int quantity;

    public ProductDetail() {
    }

    public int getId() {
        return id;
    }

    public int getProductId() {
        return productId;
    }

    public String getSize() {
        return size;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductDetail{" + "id=" + id + ", productId=" + productId + ", size='" + size + '\'' + ", quantity=" + quantity + '}';
    }
}
