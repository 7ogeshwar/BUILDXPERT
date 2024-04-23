package com.example.buildxpert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.Objects;

public class Loginpage extends AppCompatActivity {

    EditText email, loginpassword;
    Button loginbutton;
    TextView registerredirect;
    FirebaseAuth mAuth;
    ProgressBar progresbar;


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent =new Intent(getApplicationContext(),homepage.class);
            startActivity(intent);
            finish();

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        mAuth =FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        loginpassword = findViewById(R.id.passwordinput);
        registerredirect = findViewById(R.id.sinupredirecttext);
        loginbutton = findViewById(R.id.loginbutton);
        progresbar =findViewById(R.id.progressbar);

        registerredirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Loginpage.this,registerpage.class);
                startActivity(intent);
                finish();
            }
        });


        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progresbar.setVisibility(View.VISIBLE);

                String Email =  email.getText().toString();
                String password=loginpassword.getText().toString();

                if(TextUtils.isEmpty(Email)){
                    Toast.makeText(Loginpage.this,"Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils .isEmpty(password)){
                    Toast.makeText(Loginpage.this,"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }


                mAuth.signInWithEmailAndPassword(String.valueOf(email), password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progresbar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                 Toast.makeText(getApplicationContext(),"login successful",Toast.LENGTH_SHORT).show();
                                    Intent intent =new Intent(getApplicationContext(),homepage.class);
                                    startActivity(intent);
                                    finish();

                                } else {
                                    Toast.makeText(Loginpage.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });


            }
        });

    }







    }
