package com.example.cookhire;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class CookProfileActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView type,name,email,phoneNumber,cookType,setPrice;
    private CircleImageView profileImage;
    private Button backButton1,bookButton1,payButton1;

    private Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_profile);


        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("My Profile");
        //setSupportActionBar(toolbar);
//        getSupportActionBar().setTitle("My Profile");
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);


        type = findViewById(R.id.type);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        setPrice = findViewById(R.id.setPrice);
        phoneNumber = findViewById(R.id.phoneNumber);
        profileImage = findViewById(R.id.profileImage);
        backButton1 = findViewById(R.id.backButton1);
        cookType = findViewById(R.id.cookType);
        bookButton1 = findViewById(R.id.bookButton1);
        payButton1 = findViewById(R.id.payButton1);

        String str = getIntent().getStringExtra("profilepictureurl");


        //profileImage.setImageResource(getIntent().getStringExtra("profilepictureurl",0));

        type.setText(getIntent().getStringExtra("type"));
        name.setText(getIntent().getStringExtra("name"));
        email.setText(getIntent().getStringExtra("email"));
        cookType.setText(getIntent().getStringExtra("cookType"));
        setPrice.setText(getIntent().getStringExtra("setprice"));
        phoneNumber.setText(getIntent().getStringExtra("phone"));
        Glide.with(getApplicationContext()).load(str).placeholder(R.drawable.profile_image).into(profileImage);

        //final String receiverName=user.getName();
        //final String receiverId=user.getId();


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        String name = user.getDisplayName();
        String email = user.getDisplayName();
        String phone = user.getDisplayName();
        String msg = "Wants to hire you";

        final String receiverName = user.getDisplayName();
        final String receiverId = user.getProviderId();


        backButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CookProfileActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        });

        payButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(CookProfileActivity.this, PayActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }


       /* bookButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                new AlertDialog.Builder(CookProfileActivity.this)
                        .setTitle("BOOK NOW")
                        .setMessage("Send Conformation to " + user.getDisplayName() + "?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DatabaseReference reference = FirebaseDatabase.getInstance().getReference()
                                        .child("user").child(FirebaseAuth.getInstance().getUid());

                                reference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                                        String senderName = snapshot.child("name").getValue().toString();
                                        String email = snapshot.child("email").getValue().toString();
                                        String phone = snapshot.child("phone").getValue().toString();

                                        String mEmail = user.getEmail();
                                        String mSubject = "COOK HIRE";
                                        String mMessage = "Hello " + receiverName + "," + senderName +
                                                " would like to hire you:\n" +
                                                "Name: " + senderName + "\n" +
                                                "Phone number :" + phone + "\n" +
                                                "Email: " + email + "\n" +
                                                "Kindly reach out to him! \n"
                                                + "COOKHIRE-Hire the Test";

                                        DatabaseReference senderRef = FirebaseDatabase.getInstance().getReference("email")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid());

                                        senderRef.child(receiverId).setValue(true).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                DatabaseReference receiverRef1 = FirebaseDatabase.getInstance().getReference("email")
                                                        .child(receiverId);
                                                receiverRef1.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(true);

                                                DatabaseReference receiverRef2 = FirebaseDatabase.getInstance().getReference("email")
                                                        .child(receiverId);
                                                receiverRef2.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(mEmail);
                                                DatabaseReference receiverRef3 = FirebaseDatabase.getInstance().getReference("email")
                                                        .child(receiverId);
                                                receiverRef3.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(mSubject);
                                                DatabaseReference receiverRef4 = FirebaseDatabase.getInstance().getReference("email")
                                                        .child(receiverId);
                                                receiverRef4.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(mMessage);


                                            }
                                        });

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });

                            }
                        })
                        .setNegativeButton("No", null)
                        .show();




*/




              /*  Intent intent=new Intent(CookProfileActivity.this, CookProfileActivity.class);

                intent.putExtra("id",user.getUid());
                intent.putExtra("name",user.getDisplayName());
                intent.putExtra("email",user.getEmail());
                intent.putExtra("phone",user.getPhoneNumber());
                intent.putExtra("msg",msg);
                startActivity(intent);



                startActivity(intent);
                finish();

            }
        });




    }


*/




    /* @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }


    } */


}