package com.dev4fun.model;

import java.util.ArrayList;

public class Category {
    private int id;
    private String name;
    private String icon;

    private ArrayList<Product> listProducts = new ArrayList<>();;

    public Category() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Product> getListProducts() {
        return listProducts;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setListProducts(ArrayList<Product> listProducts) {
        this.listProducts = listProducts;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", listProducts=" + listProducts +
                '}';
    }
}
