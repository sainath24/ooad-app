package com.example.sai.ooad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CustomerLogIn extends AppCompatActivity {

    EditText email,password;
    Button login,registerCustomer,registerMerchant;
    String emailText,passwordText;
    TextView loginAsMerchant;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        final Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("type","customer");

        final Intent registerIntent = new Intent(this,Register.class);

        firebaseAuth = FirebaseAuth.getInstance();
        currentUser = firebaseAuth.getCurrentUser();

        if(currentUser != null) {
            //startActivity(intent);
            firebaseAuth.signOut();
        }

        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        login = findViewById(R.id.login_button);
        loginAsMerchant = findViewById(R.id.login_as_merchant);

        registerCustomer = findViewById(R.id.login_register_customer_button);
        registerMerchant = findViewById(R.id.login_register_merchant_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailText = email.getEditableText().toString();
                passwordText = password.getEditableText().toString();
                if(emailText.equals("") || passwordText.equals(""))
                    Snackbar.make(email,"Enter valid credentials", Snackbar.LENGTH_SHORT).show();
                else {
                    firebaseAuth.signInWithEmailAndPassword(emailText,passwordText).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                                startActivity(intent);
                            else
                                Toast.makeText(CustomerLogIn.this, "Authentication Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        registerCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerIntent.putExtra("type","customer");
                startActivity(registerIntent);
            }
        });

        registerMerchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerIntent.putExtra("type","merchant");
                startActivity(registerIntent);
            }
        });

        loginAsMerchant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MerchantLogin.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }
}
