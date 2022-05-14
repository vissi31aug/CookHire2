package com.example.cookhire;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    private ImageView splashScreenImage;
    private TextView logo,slogan;

    Animation topAnimation,sideAnimation;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);


        splashScreenImage=findViewById(R.id.splashScreenImage);
        logo=findViewById(R.id.logo);
        slogan=findViewById(R.id.slogan);

        topAnimation= AnimationUtils.loadAnimation(this,R.anim.logo_anmi);
        sideAnimation= AnimationUtils.loadAnimation(this,R.anim.text_anmi);

        splashScreenImage.setAnimation(topAnimation);
        logo.setAnimation(sideAnimation);
        slogan.setAnimation(sideAnimation);

        int SPLASH_SCREEN=4300;

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreen.this,LogInActivity.class);
                startActivity(intent);
                finish();
            }
        },SPLASH_SCREEN);







    }
}