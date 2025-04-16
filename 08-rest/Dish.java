package com.restaurant.rest;

import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Dish {
    private String name;
    private double price;

    public Dish() {}

    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
