package com.stuntmed.stuntmed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.stuntmed.stuntmed.R;

public class ProfileActivity extends AppCompatActivity {

    TextView name, email;
    Button log_out_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_2);




        // Assign variable
        name = findViewById(R.id.name);
        log_out_btn = findViewById(R.id.log_out);

        signOutEvent(log_out_btn);
    }

    public void handleButtonClick(View view) {
        Intent intent;

        switch (view.getId()) {
            case R.id.edit_profile_parent:
                intent = new Intent(this, Activity1.class);
                startActivity(intent);
                break;
            case R.id.edit_profile_baby:
                intent = new Intent(this, Activity2.class);
                startActivity(intent);
                break;
            case R.id.settings:
                intent = new Intent(this, Activity2.class);
                startActivity(intent);
            // Anda bisa tambahkan case lainnya jika ada tombol lain
        }
    }

    void signOutEvent(Button sign_out_btn){
        // Initialize firebase auth
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        // Initialize sign in client
        GoogleSignInClient googleSignInClient = GoogleSignIn.getClient(ProfileActivity.this, GoogleSignInOptions.DEFAULT_SIGN_IN);

        sign_out_btn.setOnClickListener(view -> {
            // Sign out from google
            googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    // Check condition
                    if (task.isSuccessful()) {
                        // When task is successful sign out from firebase
                        firebaseAuth.signOut();
                        // Display Toast
                        Toast.makeText(getApplicationContext(), "Logout successful", Toast.LENGTH_SHORT).show();
                        // Finish activity
                        finish();
                    }
                }
            });
        });
    }
}