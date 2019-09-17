package com.example.sai.ooad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MerchantLogin extends AppCompatActivity {

    EditText email,password;
    Button login;
    String e,p;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant_login);

        final Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("type","merchant");

        firebaseAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.login_merchant_email);
        password = findViewById(R.id.login_merchant_password);
        login = findViewById(R.id.login_merchant_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e = email.getEditableText().toString();
                p = password.getEditableText().toString();

                firebaseAuth.signInWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            startActivity(intent);

                        }

                    }
                });



            }
        });
    }
}
