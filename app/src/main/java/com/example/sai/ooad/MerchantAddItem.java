package com.example.sai.ooad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MerchantAddItem extends AppCompatActivity {
    EditText name,price,quantity;
    CheckBox isAvailable;
    Button submit;
    String n;
    double p,q;
    boolean avaialble;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_add_item);

        final Intent getIntent = getIntent();



        name = findViewById(R.id.add_item_name);
        price = findViewById(R.id.add_item_price);
        quantity = findViewById(R.id.add_item_quantity);
        isAvailable = findViewById(R.id.add_item_availability_checkbox);
        submit = findViewById(R.id.add_item_apply_button);

        if(getIntent.getIntExtra("flag",0) == 100) {
            name.setText(getIntent.getStringExtra("item_name"));
            price.setText(String.valueOf(getIntent.getDoubleExtra("item_price",0)));
            quantity.setText(String.valueOf(getIntent.getDoubleExtra("item_quantity",0)));
            isAvailable.setChecked(getIntent.getBooleanExtra("item_availability",true));

            position = getIntent.getIntExtra("item_position",0);

        }

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Menu/" + MerchantHome.merchantName);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                n = name.getEditableText().toString();
                p = Double.parseDouble(price.getEditableText().toString());
                q = Double.parseDouble(quantity.getEditableText().toString());
                avaialble = isAvailable.isChecked();

                MenuItem menuItem = new MenuItem(n,p,q,avaialble);
                databaseReference.child(n).setValue(menuItem);
                if(getIntent.getIntExtra("flag",0) == 100) {
                    MerchantMenuFragment.menuList.get(position).isAvailable = avaialble;
                    MerchantMenuFragment.menuList.get(position).price = p;
                    MerchantMenuFragment.menuList.get(position).quantity = q;
                    MerchantMenuFragment.menuList.get(position).name = n;
                    MerchantMenuFragment.merchantMenuRecyclerViewAdapter.notifyDataSetChanged();
                }
                onBackPressed();

            }
        });


    }
}
