package com.example.sai.ooad;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
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

public class CustomerMenuFragment extends Fragment {

    RecyclerView recyclerView;
    MerchantMenuRecyclerViewAdapter merchantMenuRecyclerViewAdapter;
    List<MenuItem> menuList;
    MenuItem menuItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.customer_merchant_list_fragment,null);

        menuList = new ArrayList<>();

        recyclerView = rootView.findViewById(R.id.customer_merchant_list_recycler_view);
        merchantMenuRecyclerViewAdapter = new MerchantMenuRecyclerViewAdapter(menuList, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(merchantMenuRecyclerViewAdapter);

        Log.i("checkmerchant",CustomerHome.selectedMerchant);

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Menu/" + CustomerHome.selectedMerchant);
        databaseReference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                menuItem = dataSnapshot.getValue(MenuItem.class);
                menuList.add(menuItem);
                merchantMenuRecyclerViewAdapter.notifyDataSetChanged();

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
