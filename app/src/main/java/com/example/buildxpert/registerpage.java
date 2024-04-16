package com.example.buildxpert;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class registerpage extends AppCompatActivity {

    EditText user_name,e_mail,pass_word;
    TextView login_redirecttext;

    Button butreg;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_registerpage);

        user_name= findViewById(R.id.username);
        e_mail= findViewById(R.id.email);
        pass_word= findViewById(R.id.password);
        butreg= findViewById(R.id.registerbutton);
        login_redirecttext =findViewById(R.id.loginredirecttext);

        butreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference=database.getReference("users");

                String name=user_name.getText().toString();
                String email=e_mail.getText().toString();
                String password=pass_word.getText().toString();

                helperclass hc= new helperclass(name,email,password);
                reference.child(name).setValue(hc);

                Toast.makeText(registerpage.this,"you have registered sucessfully",Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(registerpage.this,Loginpage.class);
                startActivity(intent);


            }
        });

        login_redirecttext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(registerpage.this,Loginpage.class);
                startActivity(intent);

            }
        });


    }
}