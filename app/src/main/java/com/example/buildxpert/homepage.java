package com.example.buildxpert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class homepage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    FirebaseAuth auth;
    Button buttonlogout,btnFadeIn;
    Button btnFadeOut, btnCrossFade, btnBlink, btnZoomIn,
            btnZoomOut, btnRotate, btnMove, btnSlideUp, btnSlideDown,
            btnBounce, btnSequential, btnTogether;
    Animation animFadeIn,animFadeOut,animBlink,animZoomIn,animZoomOut,animRotate
            ,animMove,animSlideUp,animSlideDown,animBounce,animSequential,animTogether,animCrossFadeIn,animCrossFadeOut;
    TextView txtFadeIn,txtFadeOut,txtBlink,txtZoomIn,txtZoomOut,txtRotate,txtMove,txtSlideUp,
            txtSlideDown,txtBounce,txtSeq,txtTog,txtIn,txtOut;
    Button btnLongClick;

    EditText editText;



    TextView textview;
    FirebaseUser user;
    BottomNavigationView bottomnavigationview;
    androidx.fragment.app.FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_homepage);


        btnLongClick= findViewById(R.id.btnLongClick);
        btnLongClick.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(homepage.this, "Button long clicked!", Toast.LENGTH_SHORT).show();
                // Return true to indicate the event was handled
                return true;
            }
        });
        EditText editText = findViewById(R.id.editText);

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    Toast.makeText(homepage.this, "EditText gained focus", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(homepage.this, "EditText lost focus", Toast.LENGTH_SHORT).show();
                }
            }
        });

        auth = FirebaseAuth.getInstance();
        buttonlogout=findViewById(R.id.logout);
        btnFadeIn = findViewById(R.id.btnFadeIn);
        btnBlink = (Button) findViewById(R.id.btnBlink);
        btnBounce = (Button) findViewById(R.id.btnBounce);
        btnSlideDown = (Button) findViewById(R.id.btnSlideDown);
        txtFadeIn=(TextView)findViewById(R.id.txt_fade_in);
        txtFadeOut=(TextView)findViewById(R.id.txt_fade_out);
        txtBlink=(TextView)findViewById(R.id.txt_blink);
        txtBounce=(TextView)findViewById(R.id.txt_bounce);
        txtZoomIn=(TextView)findViewById(R.id.txt_zoom_in);
        txtZoomOut=(TextView)findViewById(R.id.txt_zoom_out);
        txtSlideDown=(TextView)findViewById(R.id.txt_slide_down);
        txtRotate=(TextView)findViewById(R.id.txt_rotate);
        animSlideDown = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slide_down);
        // Slide Down
        btnSlideDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtSlideDown.startAnimation(animSlideDown);
            }
        });
        animBlink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        // blink
        btnBlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBlink.setVisibility(View.VISIBLE);
                txtBlink.startAnimation(animBlink);
            }
        });
        animBounce = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bounce);
        // Slide Down
        btnBounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBounce.startAnimation(animBounce);
            }
        });

        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);

        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        btnFadeIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                txtFadeIn.setVisibility(View.VISIBLE);
                txtFadeIn.startAnimation(animFadeIn);
            }
        });
        animBlink = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.blink);
        // blink



        //  anim=findViewById(R.id.animation);
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