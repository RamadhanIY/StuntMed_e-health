package com.stuntmed.stuntmed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.stuntmed.stuntmed.Databases.User;
import com.stuntmed.stuntmed.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.stuntmed.stuntmed.Registers.RegisterParents;
import com.stuntmed.stuntmed.SignIn;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button bt_sign_up;
    private EditText et_email, et_password,et_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // taking FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();

        // initialising all views through id defined above
        bt_sign_up = findViewById(R.id.signupbtn);
        et_name = findViewById(R.id.name);
        et_email = findViewById(R.id.email);
        et_password = findViewById(R.id.password);

        bt_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerNewUser();
            }
        });

        googleBtn(findViewById(R.id.google_btn), Register.this);
    }

    void googleBtn(Button btn, Activity this_activity){
        // Initialize sign in options the client-id is copied form google-services.json file
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("593444102839-jfu7mv63j25okrea49q2nbu87hgcgal0.apps.googleusercontent.com")
                .requestEmail()
                .build();

        // Initialize sign in client
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(this_activity, googleSignInOptions);

        btn.setOnClickListener((View.OnClickListener) view -> {
            // Initialize sign in intent
            Intent intent = googleSignInClient.getSignInIntent();
            // Start activity for result
            startActivityForResult(intent, 100);
        });

        // Initialize firebase auth
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        // Initialize firebase user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        // Check condition
        if (firebaseUser != null) {
            // When user already sign in redirect to profile activity
            startActivity(new Intent(this_activity, RegisterParents.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check condition
        if (requestCode == 100) {
            // When request code is equal to 100 initialize task
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            // check condition
            if (signInAccountTask.isSuccessful()) {
                // When google sign in successful initialize string
                String s = "Google sign in successful";
                // Display Toast
                displayToast(s);
                // Initialize sign in account
                try {
                    // Initialize sign in account
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                    // Check condition
                    if (googleSignInAccount != null) {
                        // When sign in account is not equal to null initialize auth credential
                        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                        // Check credential
                        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                        firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // Check condition
                                if (task.isSuccessful()) {
//                                    User.writeNewUser();
                                    // When task is successful redirect to profile activity display Toast
                                    startActivity(new Intent(Register.this, RegisterParents.class));
                                    displayToast("Firebase authentication successful");
                                } else {
                                    // When task is unsuccessful display Toast
                                    displayToast("Authentication Failed :" + task.getException().getMessage());
                                }
                            }
                        });
                    }
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void displayToast(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    void registerNewUser(){
        // initialising all views through id defined above
        String email, password, name;
        name = et_name.getText().toString();
        email = et_email.getText().toString();
        password = et_password.getText().toString();

        // initialising all views through id defined above
        if (TextUtils.isEmpty(name)){
            Toast.makeText(getApplicationContext(),
                            "Please enter your full name!",
                            Toast.LENGTH_SHORT)
                    .show();
            return;
        }
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

                            User.writeNewUser(null,name,email);

                            Toast.makeText(getApplicationContext(),
                                            "Registration succesful!",
                                            Toast.LENGTH_SHORT)
                                    .show();

                            // if the user created intent to login activity
                            Intent intent = new Intent(Register.this, RegisterParents.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            // Registration failed
                            Toast.makeText(getApplicationContext(),
//                                    "Registration failed!",
                                    task.getException().getLocalizedMessage(),
                                    Toast.LENGTH_SHORT)
                                    .show();
                        }
                    }
                });
    }
}