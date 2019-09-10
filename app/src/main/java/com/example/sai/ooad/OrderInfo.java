package com.example.sai.ooad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.zip.Inflater;

public class OrderInfo extends AppCompatActivity {

    RecyclerView menuList;
    TextView orderId,customerName,total;
    OrderInfoRecyclerAdapter orderInfoRecyclerAdapter;
    Orders order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_info);

        Intent intent = getIntent();

        order = (Orders)intent.getSerializableExtra("order");

        menuList = findViewById(R.id.order_info_order_recycler);
        orderId = findViewById(R.id.order_info_order_id);
        customerName = findViewById(R.id.order_info_customer_name);
        total = findViewById(R.id.order_info_total);


        menuList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        orderInfoRecyclerAdapter = new OrderInfoRecyclerAdapter(order.orderList,getApplicationContext());
        menuList.setAdapter(orderInfoRecyclerAdapter);
        orderInfoRecyclerAdapter.notifyDataSetChanged();

        orderId.setText("Order Id: " + order.orderId);
        customerName.setText("Name: " + order.customerName);
        total.setText("Total: " + String.valueOf(order.total));

    }
}
