package com.example.sai.ooad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;

public class CustomerHome extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView customerRecyclerView;
    static FragmentManager fragmentManager;
    static String selectedMerchant;
    static boolean merchantMenu = false;
    static Orders customerOrder;
    static String customerId = "testCustomer";
    static String customerName = "CustomerName";


    public static void loadFragment() {
        merchantMenu = true;
        fragmentManager.beginTransaction().replace(R.id.customer_home_fragment_container,new CustomerMenuFragment()).commit();
    }

    public void loadMerchantListFragment() {
        merchantMenu = false;
        fragmentManager.beginTransaction().replace(R.id.customer_home_fragment_container,new CustomerHomeMerchantListFragment()).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);
        toolbar = findViewById(R.id.customer_home_toolbar);
        setSupportActionBar(toolbar);

        customerOrder = new Orders();

        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.customer_home_fragment_container, new CustomerHomeMerchantListFragment()).commit();





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.customer_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.customer_toolbar_cart:
                //Open cart activity
                startActivity(new Intent(this,CustomerCart.class));
                return true;
            case R.id.customer_toolbar_logout:
                //Logout
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this,CustomerLogIn.class));
                return true;
            case R.id.customer_toolbar_past_orders:
                //Jump to past orders activity
                startActivity(new Intent(this,CustomerPastOrders.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onBackPressed() {
        if(merchantMenu)
            loadMerchantListFragment();
        else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
            //super.onBackPressed();

    }
}
