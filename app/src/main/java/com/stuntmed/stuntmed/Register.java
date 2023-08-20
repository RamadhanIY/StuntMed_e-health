package com.stuntmed.stuntmed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stuntmed.stuntmed.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.stuntmed.stuntmed.SignIn;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button bt_sign_up;
    private EditText et_email, et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // taking FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        bt_sign_up = findViewById(R.id.signupbtn);
        et_email = findViewById(R.id.username);
        et_password = findViewById(R.id.password);

        bt_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });
    }

    void registerNewUser(){
        // initialising all views through id defined above
        String email, password;
        email = et_email.getText().toString();
        password = et_password.getText().toString();

        // initialising all views through id defined above
        if (TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(),
                    "Please enter email!",
                    Toast.LENGTH_SHORT)
                    .show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                    "Please enter password!",
                    Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        // create new user or register new user
        mAuth
                .createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),
                                            "Registration succesful!",
                                            Toast.LENGTH_SHORT)
                                    .show();

                            // if the user created intent to login activity
                            Intent intent = new Intent(Register.this, SignIn.class);
                            startActivity(intent);
                        }
                        else {
                            // Registration failed
                            Toast.makeText(getApplicationContext(),
                                    "Registration failed!",
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
    }
}