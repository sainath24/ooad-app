package com.example.sai.ooad;

import java.io.Serializable;

public class MenuItem implements Serializable {
    public String name;
    public double price,quantity;
    public boolean isAvailable;

    public MenuItem() {

    }

    public MenuItem(String name, double price, double quantity, boolean isAvailable) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.isAvailable = isAvailable;
    }
}
