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

public class HirerActivity extends AppCompatActivity {

    private TextView registerHirerSignIn;
    private CircleImageView profileImageHirer;
    private TextInputEditText hirerName,registerHirerEmail,registerHirerPhoneNumber,registerHirerPassword;

    private Button registerHirerButton;

    private Uri resultUri;
    private ProgressDialog loader;
    private FirebaseAuth mAuth;
    private DatabaseReference userDataBaseRef;

    //private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hirer);

        registerHirerSignIn=findViewById(R.id.registerHirerSignIn);
        registerHirerSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HirerActivity.this,LogInActivity.class);
                startActivity(intent);
            }
        });


        registerHirerSignIn=findViewById(R.id.registerHirerSignIn);
        //spinner=findViewById(R.id.spinner);
        profileImageHirer=findViewById(R.id.profileImageHirer);
        hirerName=findViewById(R.id.hirerName);
        registerHirerEmail=findViewById(R.id.registerHirerEmail);
        registerHirerPhoneNumber=findViewById(R.id.registerHirerPhoneNumber);
        registerHirerPassword=findViewById(R.id.registerHirerPassword);
        registerHirerButton=findViewById(R.id.registerHirerButton);
        loader=new ProgressDialog(this);

        mAuth=FirebaseAuth.getInstance();



        registerHirerSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HirerActivity.this,LogInActivity.class);
                startActivity(intent);
                finish();
            }
        });






        profileImageHirer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent,1);

            }
        });



        registerHirerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final  String email=registerHirerEmail.getText().toString().trim();
                final  String name=hirerName.getText().toString().trim();
                final  String phone=registerHirerPhoneNumber.getText().toString().trim();
                final  String password=registerHirerPassword.getText().toString().trim();
               // final  String cookType=spinner.getSelectedItem().toString().trim();


                if(TextUtils.isEmpty(email)){

                    registerHirerEmail.setError("Email is Required!");
                    return;

                }


                if(TextUtils.isEmpty(name)){

                    hirerName.setError("Name is Required!");
                    return;

                }


                if(TextUtils.isEmpty(phone)){

                    registerHirerPhoneNumber.setError("Phone is Required!");
                    return;

                }


                if(TextUtils.isEmpty(password)){

                    registerHirerPassword.setError("Password is Required!");
                    return;

                }

             /*   if(cookType.equals("Select Cook Type")){

                    Toast.makeText(HirerActivity.this,"Select Cook Type",Toast.LENGTH_SHORT).show();
                    return;


                } */

                else {


                    loader.setMessage("Registering you...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (!task.isSuccessful()){

                                String error=task.getException().toString();
                                Toast.makeText(HirerActivity.this,"Error" + error,Toast.LENGTH_SHORT).show();
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
                                //userinfo.put("cookType",cookType);
                                userinfo.put("type","hirer");
                               // userinfo.put("search","hirer "+cookType);


                                userDataBaseRef.updateChildren(userinfo).addOnCompleteListener(new OnCompleteListener() {
                                    @Override
                                    public void onComplete(@NonNull Task task) {

                                        if(!task.isSuccessful()){
                                            Toast.makeText(HirerActivity.this,"Data is set Successfully",Toast.LENGTH_SHORT).show();
                                        }
                                        else {

                                            Toast.makeText(HirerActivity.this,"Data is set Successfully",Toast.LENGTH_SHORT).show();
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

                                            Toast.makeText(HirerActivity.this,"Image upload failed..",Toast.LENGTH_SHORT).show();

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

                                                                    Toast.makeText(HirerActivity.this,"Image url added to the database successfully..",Toast.LENGTH_SHORT).show();

                                                                }

                                                                else {
                                                                    Toast.makeText(HirerActivity.this,task.getException().toString(),Toast.LENGTH_SHORT).show();

                                                                }


                                                            }
                                                        });

                                                        finish();

                                                    }
                                                });
                                            }

                                        }
                                    });

                                    Intent intent=new Intent(HirerActivity.this,MainActivity.class);
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
            profileImageHirer.setImageURI(resultUri);
        }

    }

    }
