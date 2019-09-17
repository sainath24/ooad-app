package com.example.sai.ooad;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public class MerchantHome extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout fragmentContainer;
    FragmentManager fragmentManager;
    static String merchantName;
    static String merchantId;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_home);

        toolbar = findViewById(R.id.merchant_home_toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();


        bottomNavigationView = (BottomNavigationView)findViewById(R.id.merchant_navigation_view);
        fragmentContainer = findViewById(R.id.merchant_home_fragment);

        fragmentManager.beginTransaction().replace(R.id.merchant_home_fragment,new MerchantPendingOrdersFragment()).commit();




        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId()) {
                    case R.id.merchant_pending_orders:
                        fragmentManager.beginTransaction().replace(R.id.merchant_home_fragment,new MerchantPendingOrdersFragment()).commit();
                        break;
                    case R.id.merchant_menu:
                        MerchantMenuFragment merchantMenuFragment = new MerchantMenuFragment();
                        fragmentManager.beginTransaction().replace(R.id.merchant_home_fragment,merchantMenuFragment).commit();
                        break;
                    case R.id.mercahnt_past_orders:
                        fragmentManager.beginTransaction().replace(R.id.merchant_home_fragment,new MerchantPastOrdersFragment()).commit();
                        break;
                }
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.merchant_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.merchant_toolbar_logout:
                //Logout
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this,CustomerLogIn.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
