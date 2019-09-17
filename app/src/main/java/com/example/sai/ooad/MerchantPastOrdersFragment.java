package com.example.sai.ooad;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MerchantPastOrdersFragment extends Fragment {
    RecyclerView recyclerView;
    static MerchantPendingOrderRecyclerAdapter merchantPendingOrderRecyclerAdapter;
    List<Orders> ordersList;
    Orders orders;
    DatabaseReference databaseReference,customerOrderDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.merchant_pending_orders_fragment,null);

        recyclerView = rootView.findViewById(R.id.mercahnt_pending_orders_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ordersList = new ArrayList<>();
        merchantPendingOrderRecyclerAdapter = new MerchantPendingOrderRecyclerAdapter(ordersList,getContext());
        recyclerView.setAdapter(merchantPendingOrderRecyclerAdapter);
        merchantPendingOrderRecyclerAdapter.notifyDataSetChanged();

        databaseReference = FirebaseDatabase.getInstance().getReference("Orders/"+MerchantHome.merchantName);
        databaseReference.orderByValue().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                customerOrderDatabase = FirebaseDatabase.getInstance().getReference("Orders/"+MerchantHome.merchantName+"/"+dataSnapshot.getKey());

                customerOrderDatabase.orderByChild("time").addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Log.i("merchantPending",dataSnapshot.getValue(Orders.class).toString());
                        orders = dataSnapshot.getValue(Orders.class);
                        if(orders.isCompleted) {
                            ordersList.add(orders);
                            merchantPendingOrderRecyclerAdapter.notifyDataSetChanged();
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

        return rootView;
    }
}
