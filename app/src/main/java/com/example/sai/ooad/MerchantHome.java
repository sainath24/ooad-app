package com.example.sai.ooad;

import androidx.annotation.NonNull;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

public class MerchantHome extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    FrameLayout fragmentContainer;
    FragmentManager fragmentManager;
    static String merchantName = "testing123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_home);

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
                        break;
                }
                return true;
            }
        });




    }
}
