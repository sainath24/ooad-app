package com.example.sai.ooad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CustomerCart extends AppCompatActivity {

    RecyclerView recyclerView;
    static CustomerCartRecyclerAdapter customerCartRecyclerAdapter;
    static TextView total;
    Button checkout;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_cart);

        total = findViewById(R.id.customer_cart_total);
        checkout = findViewById(R.id.customer_cart_checkout_button);
        recyclerView = findViewById(R.id.customer_cart_recycler_view);

        customerCartRecyclerAdapter = new CustomerCartRecyclerAdapter(CustomerHome.customerOrder.orderList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(customerCartRecyclerAdapter);
        customerCartRecyclerAdapter.notifyDataSetChanged();

        total.setText("Total: " + String.valueOf(CustomerHome.customerOrder.total));

        databaseReference = FirebaseDatabase.getInstance().getReference("Orders/"+CustomerHome.selectedMerchant+"/"+CustomerHome.customerId);


        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child(databaseReference.push().getKey()).setValue(CustomerHome.customerOrder);
                Snackbar.make(checkout,"Order Verified",Snackbar.LENGTH_LONG);
                CustomerHome.customerOrder.clear();
                finish();
            }
        });


    }
}
