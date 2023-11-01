package com.example.myapplicatiologin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.tasks.OnCompleteListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registeractivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private Object newOnCompleteListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeractivity);
        mAuth= FirebaseAuth.getInstance();
        if(mAuth.getCurrentUser()!=null){
            finish();
            return;
        }
        Button btnregister= findViewById(R.id.btnregister);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
        TextView textViewSwitchToLogin= findViewById(R.id.btnlogin);
        textViewSwitchToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToLogin();
            }
        });
    }
    private void registerUser(){
        EditText email=findViewById(R.id.et_email);
        EditText gender= findViewById(R.id.et_gender);
        EditText passwd=findViewById(R.id.et_passwd);
        EditText address=findViewById(R.id.et_address);

        String emailadd=email.getText().toString();
        String genderadd=gender.getText().toString();
        String addressadd=address.getText().toString();
        String passwadd=passwd.getText().toString();

        if(emailadd.isEmpty() ||genderadd.isEmpty()||passwadd.isEmpty()||addressadd.isEmpty()){
            Toast.makeText(this, "please fill all fields", Toast.LENGTH_SHORT).show();
            return;
        }
        Task<AuthResult> authResultTask = mAuth.createUserWithEmailAndPassword(emailadd,passwadd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            User user = new User(emailadd, addressadd, genderadd, passwadd);
                            DatabaseReference users = FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            users.setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    showMainActivity();
                                    // Your code here
                                }
                            });

                        }else{
                            Toast.makeText(Registeractivity.this,"Authentication failed.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }
    private void showMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    private void switchToLogin(){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    }



