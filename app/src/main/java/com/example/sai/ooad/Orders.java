package com.example.sai.ooad;

import android.os.CpuUsageInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Orders implements Serializable {
    public List<MenuItem> orderList;
    public double total;
    public boolean isCompleted;
    public String orderId,customerName,customerId;
    public Date time;

    public Orders() {
        orderList = new ArrayList<>();
        total = 0;
        isCompleted = false;
        customerName = CustomerHome.customerName;
        time = new Date();
        customerId = CustomerHome.customerId;
    }

    public void setTotal(double price) {
        total += price;
    }
    public void clear() {
        orderList.clear();
        total = 0;
        orderId = "";
    }
}
