package com.example.sai.ooad;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class MerchantPendingOrdersFragment extends Fragment {

    RecyclerView recyclerView;
    List<Orders> ordersList;
    static MerchantPendingOrderRecyclerAdapter merchantPendingOrderRecyclerAdapter;
    DatabaseReference databaseReference;
    DatabaseReference customerOrderDatabase;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.merchant_pending_orders_fragment,null);

        ordersList = new ArrayList<>();
        recyclerView = rootView.findViewById(R.id.mercahnt_pending_orders_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        merchantPendingOrderRecyclerAdapter = new MerchantPendingOrderRecyclerAdapter(ordersList,getContext());
        recyclerView.setAdapter(merchantPendingOrderRecyclerAdapter);
        merchantPendingOrderRecyclerAdapter.notifyDataSetChanged();

        databaseReference = FirebaseDatabase.getInstance().getReference("Orders/"+MerchantHome.merchantName);
        databaseReference.orderByValue().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                customerOrderDatabase = FirebaseDatabase.getInstance().getReference("Orders/"+MerchantHome.merchantName+"/"+dataSnapshot.getKey());

                customerOrderDatabase.orderByValue().addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                        Log.i("merchantPending",dataSnapshot.getValue(Orders.class).toString());
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
