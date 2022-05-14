package com.example.cookhire;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView type,name,email,phoneNumber,cookType,setPrice;
    private CircleImageView profileImage;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar=findViewById(R.id.toolbar);
        toolbar.setTitle("My Profile");
        //setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("My Profile");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);


        type=findViewById(R.id.type);
        name=findViewById(R.id.name);
        email=findViewById(R.id.email);
        setPrice=findViewById(R.id.setPrice);
        phoneNumber=findViewById(R.id.phoneNumber);
        profileImage=findViewById(R.id.profileImage);
        backButton=findViewById(R.id.backButton);
        cookType=findViewById(R.id.cookType);


        DatabaseReference reference= FirebaseDatabase.getInstance().getReference()
               .child("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){

                    type.setText(snapshot.child("type").getValue().toString());
                    name.setText(snapshot.child("name").getValue().toString());
                    email.setText(snapshot.child("email").getValue().toString());
                    phoneNumber.setText(snapshot.child("phone").getValue().toString());

                    //setPrice.setText(snapshot.child("setprice").getValue().toString());


                    String value1 =snapshot.child("type").getValue().toString();
                    if(value1.equals("cook") && value1 != null){
                        cookType.setText(snapshot.child("cookType").getValue().toString());
                        cookType.setVisibility(View.VISIBLE);

                    }else{
                        cookType.setVisibility(View.GONE);
                    }


                    String value =snapshot.child("type").getValue().toString();

                    if(value.equals("cook") && value != null){
                        setPrice.setText(snapshot.child("setprice").getValue().toString());
                        setPrice.setVisibility(View.VISIBLE);

                    }else{
                        setPrice.setVisibility(View.GONE);
                    }


                    if(snapshot.hasChild("profilepictureurl")) {
                        Glide.with(getApplicationContext()).load(snapshot.child("profilepictureurl").getValue().toString()).placeholder(R.drawable.profile_image).into(profileImage);

                    }
                    else {
                        profileImage.setImageResource(R.drawable.profile_image);
                    }


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(ProfileActivity.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });



    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }



    }

    public void update(View view){




    }

}