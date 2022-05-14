package com.example.cookhire;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class CookRegistrationActivity extends AppCompatActivity {

    private TextView registerCookSignIn;
    private CircleImageView profileImage;
    private TextInputEditText registerCookName,registerCookEmail,registerCookPhoneNumber,registerCookPassword,setPrice;

    private Button registerCookSignUpButton;

    private Uri resultUri;
    private ProgressDialog loader;
    private FirebaseAuth mAuth;
    private DatabaseReference userDataBaseRef;

    private Spinner spinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cook_registration);

        registerCookSignIn=findViewById(R.id.registerCookSignIn);
        spinner=findViewById(R.id.spinner);
        profileImage=findViewById(R.id.profileImage);
        registerCookName=findViewById(R.id.registerCookName);
        registerCookEmail=findViewById(R.id.registerCookEmail);
        registerCookPhoneNumber=findViewById(R.id.registerCookPhoneNumber);
        registerCookPassword=findViewById(R.id.registerCookPassword);
        registerCookSignUpButton=findViewById(R.id.registerCookSignUpButton);
        setPrice=findViewById(R.id.setPrice);

        loader=new ProgressDialog(this);

        mAuth=FirebaseAuth.getInstance();



        registerCookSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CookRegistrationActivity.this,LogInActivity.class);
                startActivity(intent);
                finish();
            }
        });






        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1);

            }
        });



        registerCookSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final  String email=registerCookEmail.getText().toString().trim();
                final  String name=registerCookName.getText().toString().trim();
                final  String phone=registerCookPhoneNumber.getText().toString().trim();
                final  String password=registerCookPassword.getText().toString().trim();
                final  String cookType=spinner.getSelectedItem().toString().trim();
                final  String setprice=setPrice.getText().toString().trim();
               


                if(TextUtils.isEmpty(email)){

                    registerCookEmail.setError("Email is Required!");
                    return;

                }


                if(TextUtils.isEmpty(name)){

                    registerCookName.setError("Name is Required!");
                    return;

                }


                if(TextUtils.isEmpty(phone)){

                    registerCookPhoneNumber.setError("Mobile number is Required!");
                    return;

                }


                if(TextUtils.isEmpty(password)){

                    registerCookPhoneNumber.setError("Password is Required!");
                    return;

                }

                if(cookType.equals("Select Cook Type")){

                    Toast.makeText(CookRegistrationActivity.this,"Select Cook Type",Toast.LENGTH_SHORT).show();
                    return;


                }

                if(TextUtils.isEmpty(setprice)){

                    setPrice.setError("Set Price!");
                    return;

                }


                else {


                    loader.setMessage("Registering you...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()){

                                String error=task.getException().toString();
                                Toast.makeText(CookRegistrationActivity.this,"Error" + error,Toast.LENGTH_SHORT).show();
                                loader.dismiss();

                            }



                            else{

                                String currentUserId=mAuth.getCurrentUser().getUid();
                                userDataBaseRef= FirebaseDatabase.getInstance().getReference()
                                .child("user").child(currentUserId);


                                HashMap userinfo=new HashMap();
                                userinfo.put("id",currentUserId);
                                userinfo.put("name",name);
                                userinfo.put("email",email);
                                userinfo.put("phone",phone);
                                userinfo.put("password",password);
                                userinfo.put("cookType",cookType);
                                userinfo.put("setprice",setprice);
                                userinfo.put("type","cook");
                                userinfo.put("search","cook"+cookType);


                                userDataBaseRef.updateChildren(userinfo).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {

                                        if(!task.isSuccessful()){
                                            Toast.makeText(CookRegistrationActivity.this,"Data is set Successfully",Toast.LENGTH_SHORT).show();
                                        }
                                        else {

                                            Toast.makeText(CookRegistrationActivity.this,"Data is set Successfully",Toast.LENGTH_SHORT).show();
                                        }

                                        finish();
                                        //loader.dismiss();

                                    }
                                });


                                if (resultUri!=null){

                                    final StorageReference filePath= FirebaseStorage.getInstance().getReference()
                                            .child("profile image").child(currentUserId);


                                    Bitmap bitmap=null;

                                    try {


                                        bitmap= MediaStore.Images.Media.getBitmap(getApplication().getContentResolver(),resultUri);
                                    }

                                    catch (IOException e){

                                        e.printStackTrace();

                                    }

                                    ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                                    bitmap.compress(Bitmap.CompressFormat.JPEG,20,byteArrayOutputStream);
                                    byte[] data=byteArrayOutputStream.toByteArray();
                                    UploadTask uploadTask=filePath.putBytes(data);


                                    uploadTask.addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {

                                            Toast.makeText(CookRegistrationActivity.this,"Image upload failed..",Toast.LENGTH_SHORT).show();

                                        }
                                    });


                                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                            if (taskSnapshot.getMetadata() !=null && taskSnapshot.getMetadata().getReference()!=null){
                                                Task<Uri>  result=taskSnapshot.getStorage().getDownloadUrl();
                                                result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                                                    @Override
                                                    public void onSuccess(Uri uri) {

                                                        String imageUrl=uri.toString();
                                                        Map newImageMap=new HashMap();
                                                        newImageMap.put("profilepictureurl",imageUrl);

                                                        userDataBaseRef.updateChildren(newImageMap).addOnCompleteListener(new OnCompleteListener() {
                                                            @Override
                                                            public void onComplete(@NonNull Task task) {


                                                                if (task.isSuccessful()){

                                                                    Toast.makeText(CookRegistrationActivity.this,"Image url added to the database successfully..",Toast.LENGTH_SHORT).show();

                                                                }

                                                                else {
                                                                    Toast.makeText(CookRegistrationActivity.this,task.getException().toString(),Toast.LENGTH_SHORT).show();

                                                                }


                                                            }
                                                        });

                                                        finish();

                                                    }
                                                });
                                            }

                                        }
                                    });

                                    Intent intent=new Intent(CookRegistrationActivity.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                    loader.dismiss();

                                }


                            }


                        }
                    });

                }


            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data!=null){
            resultUri=data.getData();
            profileImage.setImageURI(resultUri);
        }

    }
}