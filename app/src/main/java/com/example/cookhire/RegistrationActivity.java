package com.example.cookhire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private Button registerCookButton,hirerButton;

    private TextView backTextButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        registerCookButton=findViewById(R.id.registerCookButton);
        hirerButton=findViewById(R.id.hirerButton);
        backTextButton=findViewById(R.id.backTextButton);


        registerCookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(RegistrationActivity.this,CookRegistrationActivity.class);
                startActivity(intent);
                finish();


            }
        });


        backTextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(RegistrationActivity.this,LogInActivity.class);
                startActivity(intent);
                finish();

            }
        });


        hirerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(RegistrationActivity.this,HirerActivity.class);
                startActivity(intent);
                finish();

            }
        });



    }
}