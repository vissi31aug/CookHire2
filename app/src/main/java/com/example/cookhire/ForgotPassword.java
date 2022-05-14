package com.example.cookhire;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    private Button forgotPasswordButton;
    private TextInputEditText forgotEmail;
    private TextView backToLogin;
    private String email;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);


        forgotPasswordButton=findViewById(R.id.forgotPasswordButton);
        forgotEmail=findViewById(R.id.forgotEmail);
        backToLogin=findViewById(R.id.backToLogin);

        auth=FirebaseAuth.getInstance();


        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(ForgotPassword.this,LogInActivity.class);
                startActivity(intent);
                finish();

            }
        });

         forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 validateData();

             }
         });



    }

    private void validateData() {

        email=forgotEmail.getText().toString();
        if (email.isEmpty()){
            forgotEmail.setError("Required");
        }
        else {

            forgetPassword();

        }
    }

    private void forgetPassword() {

        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(ForgotPassword.this,"Check Your Email",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgotPassword.this,LogInActivity.class));
                            finish();
                        }
                        else {
                            Toast.makeText(ForgotPassword.this,"Error Occurred: "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }
}