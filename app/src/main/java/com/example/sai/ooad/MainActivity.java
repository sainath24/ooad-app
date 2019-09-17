package com.example.sai.ooad;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    static boolean isCustomer = true;
    DatabaseReference databaseReference;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");

        Intent intent = getIntent();
        if(intent.getStringExtra("type").equals("customer")) {
            isCustomer = true;
            databaseReference = databaseReference.child("customers");
            Query query = databaseReference.child(currentUser.getUid()).orderByKey();
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot == null) {
                        Toast.makeText(MainActivity.this, "Invalid Login, are you sure you are a customer?", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), CustomerLogIn.class));
                        finish();
                    }
                    else {
                        CustomerHome.customerId = currentUser.getUid();
                        CustomerHome.customerName = currentUser.getDisplayName();
                        startActivity(new Intent(getApplicationContext(),CustomerHome.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        else if(intent.getStringExtra("type").equals("merchant")) {
            isCustomer = false;
            databaseReference = databaseReference.child("customers");
            Query query = databaseReference.child(currentUser.getUid()).orderByKey();
            query.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if(dataSnapshot == null) {
                        Toast.makeText(MainActivity.this, "Invalid Login, are you sure you are a merchant?", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), CustomerLogIn.class));
                        finish();
                    }
                    else {
                        MerchantHome.merchantName = currentUser.getDisplayName();
                        MerchantHome.merchantId = currentUser.getUid();
                        startActivity(new Intent(getApplicationContext(),MerchantHome.class));
                        finish();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        /*DatabaseReference db = FirebaseDatabase.getInstance().getReference("Menu");
        db.orderByKey().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.i("TESTING",dataSnapshot.toString());
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
        }); */


    }
}
