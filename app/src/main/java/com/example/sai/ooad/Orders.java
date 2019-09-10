package com.example.sai.ooad;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    public List<MenuItem> orderList;
    public double total;
    public boolean isCompleted;

    public Orders() {
        orderList = new ArrayList<>();
        total = 0;
        isCompleted = false;
    }

    public void setTotal(double price) {
        total += price;
    }
    public void clear() {
        orderList.clear();
        total = 0;
    }
}
