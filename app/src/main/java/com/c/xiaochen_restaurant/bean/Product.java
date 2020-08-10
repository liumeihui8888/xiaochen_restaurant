package com.c.xiaochen_restaurant.bean;

import java.io.Serializable;

public class Product implements Serializable {
    protected int id;
    protected String name;
    protected String label;
    protected String description;
    protected float price;
    protected String icon;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    protected Restaurant restaurant;

    public Product (){}
    public Product(String name,String label,String description,String url,float price){
        this.name=name;
        this.label=label;
        this.description=description;
        this.icon=url;
        this.price=price;

    }
}

