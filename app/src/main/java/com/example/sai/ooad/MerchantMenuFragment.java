package com.example.sai.ooad;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

public class MerchantMenuFragment extends Fragment {

    FloatingActionButton addItem;
    RecyclerView menu;
    static MerchantMenuRecyclerViewAdapter merchantMenuRecyclerViewAdapter;
    static List<MenuItem> menuList;
    DatabaseReference databaseReference;
    MenuItem menuItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.merchant_menu_fragment,null);

        databaseReference = FirebaseDatabase.getInstance().getReference("Menu/testing123");

        addItem = (FloatingActionButton)rootView.findViewById(R.id.merchant_menu_add_item);
        menu = rootView.findViewById(R.id.merchant_menu_recyclerview);

        menuList = new ArrayList<MenuItem>();

        merchantMenuRecyclerViewAdapter = new MerchantMenuRecyclerViewAdapter(menuList,getContext());
        menu.setLayoutManager(new LinearLayoutManager(getContext()));
        menu.setAdapter(merchantMenuRecyclerViewAdapter);

        databaseReference.orderByKey().addChildEventListener(new ChildEventListener() {
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




        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),MerchantAddItem.class));
            }
        });



        return rootView;
    }
}
