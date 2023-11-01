package com.example.myapplicatiologin;

//import  android.os.Build.VERSION_CODES.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.annotation.SuppressLint;


public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {
            finish();
            return;
        }
        Button btnlogin = findViewById(R.id.btnlogin);
        btnlogin.setOnClickListener((new OnClickListener() {
            @Override
            public void onClick(View v) {
                authenticateUser();
            }
        }));

        TextView tvSwitchToRegister = findViewById(R.id.btnregister);
        tvSwitchToRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToRegister();
            }
        });
    }

    public void authenticateUser() {
        EditText etemail = findViewById(R.id.email);
        EditText epassword = findViewById(R.id.passwd);

        String email = etemail.getText().toString();
        String passwd = epassword.getText().toString();

        if (email.isEmpty() || passwd.isEmpty()) {
            Toast.makeText(this, "please fill all the fields", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, passwd)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            showMainActivity();
                        } else {
                            Toast.makeText(LoginActivity.this, "authentication faied.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void showMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }

    private void switchToRegister() {
        Intent intent = new Intent(this, Registeractivity.class);
        startActivity(intent);



    EditText email_tv,password_et;
         EditText email=(EditText)findViewById(R.id.email);
        password_et=(EditText)findViewById(R.id.passwd);

        Button login_btn, register_btn;
        login_btn=(Button) findViewById(R.id.btnlogin);
        register_btn=(Button) findViewById(R.id.btnregister);
        //TextView forgot =(TextView) findViewById(R.id.forgot);

        register_btn.setOnClickListener(v -> {

            Intent i=new Intent(getApplicationContext(),Registeractivity.class);
            startActivity(i);
        });
    }
}




