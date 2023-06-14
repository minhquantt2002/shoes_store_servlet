package com.dev4fun.model;

import java.util.ArrayList;

public class Product {
    private int id;
    private int categoryId;
    private Category category;
    private String name;
    private String description;
    private String imageLink;
    private String imageList;
    private float price;
    private float cost;
    private String status;
    private int totalQuantity;
    private String createdAt;
    private int sold;
    private ArrayList<ProductDetail> productDetails;
    private ArrayList<Comment> comments;

    public Product() {
        this.productDetails = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<ProductDetail> getProductDetails() {
        return productDetails;
    }

    public int getId() {
        return id;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getImageList() {
        return imageList;
    }

    public float getPrice() {
        return price;
    }

    public int getTotalQuantity() {
        updateTotalQuantity();
        return totalQuantity;
    }

    public void updateTotalQuantity() {
        int total = 0;
        for (ProductDetail productDetail : productDetails) {
            total += productDetail.getQuantity();
        }
        totalQuantity = total;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public void setImageList(String imageList) {
        this.imageList = imageList;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setProductDetails(ArrayList<ProductDetail> productDetails) {
        this.productDetails = productDetails;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", categoryId=" + categoryId + ", category=" + category + ", name='" + name + '\'' + ", description='" + description + '\'' + ", imageLink='" + imageLink + '\'' + ", imageList='" + imageList + '\'' + ", price=" + price + ", cost=" + cost + ", status='" + status + '\'' + ", totalQuantity=" + totalQuantity + ", createdAt='" + createdAt + '\'' + ", sold=" + sold + ", productDetails=" + productDetails + '}';
    }
}
