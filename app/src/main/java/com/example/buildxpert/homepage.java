package com.example.buildxpert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    FirebaseAuth auth;
    Button buttonlogout;

    TextView textview;
    FirebaseUser user;
    BottomNavigationView bottomnavigationview;
    androidx.fragment.app.FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_homepage);

        auth = FirebaseAuth.getInstance();
        buttonlogout=findViewById(R.id.logout);
        textview =findViewById(R.id.userdetails);
        user =auth.getCurrentUser();
        if(user == null){
            Intent intent = new Intent(getApplicationContext(), Loginpage.class);
            startActivity(intent);
            finish();
      }
        else{
            textview.setText(user.getEmail());
        }
        buttonlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), Loginpage.class);
                startActivity(intent);
                finish();

            }
        });


        bottomnavigationview =findViewById(R.id.bottomnavigation);
        bottomnavigationview.setBackground(null);
        bottomnavigationview.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemid =item.getItemId();
            if(itemid == R.id.notification){openfragment(new notificationfragment()); return  true;}
            else if (itemid == R.id.settings) {
                openfragment(new settingsfragment());
                return true;
            } else if (itemid ==R.id.guide) {
                openfragment(new guidefragmewnt());
                return true;
            }
            else if (itemid ==R.id.home) {
                openfragment(new homefragment());
                return true;
            }

                return false;
            }
        });

        fragmentManager = getSupportFragmentManager();
        openfragment(new homefragment());

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }


    private void openfragment(Fragment fragment){
        FragmentTransaction transaction =fragmentManager.beginTransaction();
       transaction.replace(R.id.fragmentcontainer,fragment);
       transaction.commit();
    }


}