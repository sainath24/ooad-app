package com.example.sai.ooad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText name,email,password,password2;
    String n,e,p,p2;
    Button register;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Intent intent = getIntent();

        if(intent.getStringExtra("type").equals("customer"))
            databaseReference = FirebaseDatabase.getInstance().getReference("Users/customers");
        else if(intent.getStringExtra("type").equals("merchant"))
            databaseReference = FirebaseDatabase.getInstance().getReference("Users/merchants");

        firebaseAuth = FirebaseAuth.getInstance();

        name = findViewById(R.id.register_name);
        email = findViewById(R.id.register_email);
        password = findViewById(R.id.register_password);
        password2 = findViewById(R.id.register_password_again);
        register = findViewById(R.id.register_button);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p = password.getEditableText().toString();
                p2 = password2.getEditableText().toString();
                if(!p.equals(p2)) {
                    Toast.makeText(Register.this, "Passwords do not match! Re-enter password", Toast.LENGTH_SHORT).show();
                    password.setText("");
                    password2.setText("");
                }
                else {
                    n = name.getEditableText().toString();
                    e = email.getEditableText().toString();
                    firebaseAuth.createUserWithEmailAndPassword(e,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                firebaseUser = firebaseAuth.getCurrentUser();

                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(n).build();
                                firebaseUser.updateProfile(profileUpdates).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()) {
                                            databaseReference.child(firebaseUser.getUid()).setValue(firebaseUser.getDisplayName());
                                            Toast.makeText(Register.this, "User Registered", Toast.LENGTH_SHORT).show();
                                            firebaseAuth.signOut();
                                            startActivity(new Intent(getApplicationContext(),CustomerLogIn.class));
                                        }
                                    }
                                });

                            }

                        }
                    });

                }

            }
        });

    }
}
