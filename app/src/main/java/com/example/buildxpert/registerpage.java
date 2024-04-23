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
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class registerpage extends AppCompatActivity {

    EditText email,password;
    TextView login_redirecttext;
    Button butreg;
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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_registerpage);
        mAuth =FirebaseAuth.getInstance();
        email= findViewById(R.id.email);
        password= findViewById(R.id.password);
        butreg= findViewById(R.id.registerbutton);
        login_redirecttext =findViewById(R.id.loginredirecttext);
        progresbar =findViewById(R.id.progressbar);

        login_redirecttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registerpage.this,Loginpage.class);
                startActivity(intent);
                finish();

            }
        });
        butreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              progresbar.setVisibility(View.VISIBLE);
                String emaill,pass;
                 emaill=String.valueOf(email.getText());
                 pass=String.valueOf(password.getText());

                if(TextUtils .isEmpty(emaill)){
                    Toast.makeText(registerpage.this,"Enter Email",Toast.LENGTH_SHORT).show();
                              return;
                }
                if(TextUtils .isEmpty(pass)){
                    Toast.makeText(registerpage.this,"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }


                mAuth.createUserWithEmailAndPassword(emaill, pass)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    progresbar.setVisibility(View.GONE);
                                    Toast.makeText(registerpage.this, "Account created",
                                            Toast.LENGTH_SHORT).show();

                                } else {

                                    Toast.makeText(registerpage.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }
                        });





            }
        });




    }
}