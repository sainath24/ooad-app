package com.example.sai.ooad;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CustomerPastOrders extends AppCompatActivity {

    RecyclerView recyclerView;
    MerchantPendingOrderRecyclerAdapter merchantPendingOrderRecyclerAdapter;
    List<Orders> ordersList;
    Orders order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_past_orders);

        recyclerView = findViewById(R.id.customer_past_orders_recycler);
        ordersList = new ArrayList<>();
        merchantPendingOrderRecyclerAdapter = new MerchantPendingOrderRecyclerAdapter(ordersList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(merchantPendingOrderRecyclerAdapter);
        merchantPendingOrderRecyclerAdapter.notifyDataSetChanged();

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Orders");
        databaseReference.orderByValue().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.i("pastOrdersMerchant",dataSnapshot.getKey());
                final DatabaseReference userOrderDatabase = databaseReference.child(dataSnapshot.getKey());//FirebaseDatabase.getInstance().getReference(dataSnapshot.getKey());
                userOrderDatabase.orderByValue().addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Log.i("pastOrdersCustomerId",dataSnapshot.getKey());
                        if(dataSnapshot.getKey().toString().equals(CustomerHome.customerId)) {
                            DatabaseReference ordersDatabase = userOrderDatabase.child(CustomerHome.customerId);//FirebaseDatabase.getInstance().getReference(CustomerHome.customerId);
                            ordersDatabase.orderByChild("time").addChildEventListener(new ChildEventListener() {
                                @Override
                                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                                    ordersList.add(dataSnapshot.getValue(Orders.class));
                                    merchantPendingOrderRecyclerAdapter.notifyDataSetChanged();
                                }

                                @Override
                                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                }

                                @Override
                                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                                }

                                @Override
                                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
