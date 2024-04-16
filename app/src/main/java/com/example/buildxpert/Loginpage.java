package com.example.buildxpert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Loginpage extends AppCompatActivity {

    EditText loginname, loginpassword;
    Button loginbutton;
    TextView registerredirect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);


        loginname = findViewById(R.id.usernameinput);
        loginpassword = findViewById(R.id.passwordinput);
        registerredirect = findViewById(R.id.sinupredirecttext);
        loginbutton = findViewById(R.id.loginbutton);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validuser() | !validpassword()){

                }else{
                    checkuser();
                }
            }
        });
        registerredirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Loginpage.this,registerpage.class);
                startActivity(intent);
            }
        });
    }


    public Boolean validuser() {
        String val = loginname.getText().toString();
        if (val.isEmpty()) {
            loginname.setError("username cannot be empty");
            return false;
        }
        else {
            loginname.setError(null);
            return true;
        }
    }


    public Boolean validpassword() {
        String val = loginpassword.getText().toString();
        if (val.isEmpty()) {
            loginpassword.setError("password cannot be empty");
            return false;
        }
        else {
            loginpassword.setError(null);
            return true;
        }
    }

    public void checkuser(){
        String username= loginname.getText().toString().trim();
        String userpassword= loginpassword.getText().toString().trim();


        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkuserdatabase = reference.orderByChild("username").equalTo(username);

        checkuserdatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    loginname.setError(null);
                    String passwordfromdb = snapshot.child(username).child("password").getValue(String.class);
                    if(passwordfromdb.equals(userpassword)){
                        loginpassword.setError(null);
                        Intent intent = new Intent(Loginpage.this, homepage.class);
                        startActivity(intent);
                    }
                    else{
                        loginpassword.setError("invalid credentials");
                        loginpassword.requestFocus();
                    }

                }else{
                    loginname.setError("user does not exits");
                    loginname.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}