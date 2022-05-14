package com.example.cookhire.Message;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cookhire.MainActivity;
import com.example.cookhire.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotificationActivity extends AppCompatActivity {

    private TextInputEditText hirerName;
    private Button backButton;
    private TextView id,name,email,phoneNumber,msgg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);


        hirerName=findViewById(R.id.hirerName);
        backButton=findViewById(R.id.backButton);


        id=findViewById(R.id.id);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        phoneNumber=findViewById(R.id.phoneNumber);
        msgg=findViewById(R.id.msgg);



/*
        id.setText(getIntent().getStringExtra("id"));
        name.setText(getIntent().getStringExtra("name"));
        email.setText(getIntent().getStringExtra("email"));
        phoneNumber.setText(getIntent().getStringExtra("phone"));
        msgg.setText(getIntent().getStringExtra("msgg"));



*/





        DatabaseReference reference= FirebaseDatabase.getInstance().getReference()
                .child("email").child(FirebaseAuth.getInstance().getCurrentUser().getUid());




        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists()) {

                    msgg.setText(snapshot.getValue().toString());

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent =new Intent(NotificationActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }
}