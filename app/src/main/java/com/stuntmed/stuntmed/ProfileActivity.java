package com.stuntmed.stuntmed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stuntmed.stuntmed.Databases.User;
import com.stuntmed.stuntmed.Profiles.EditProfileBaby;
import com.stuntmed.stuntmed.Profiles.EditProfileParents;
import com.stuntmed.stuntmed.Profiles.Settings;
import com.stuntmed.stuntmed.R;

public class ProfileActivity extends AppCompatActivity {

    TextView name, phone_number, country, address;
    Button log_out_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_2);

        ImageView backButton = (ImageView) findViewById(R.id.backButtonprofiles);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        // Assign variable
        name = findViewById(R.id.name);
        phone_number = findViewById(R.id.phone_number);
        country = findViewById(R.id.country);
        address = findViewById(R.id.address);

        log_out_btn = findViewById(R.id.log_out);

        getData();
        getData();

        signOutEvent(log_out_btn);
    }

    public void handleButtonClick(View view) {
        Intent intent;
        int id = view.getId();

        if (id == R.id.edit_profile_parent) {
            intent = new Intent(this, EditProfileParents.class);
            startActivity(intent);
        } else if (id == R.id.edit_profile_baby) {
            intent = new Intent(this, EditProfileBaby.class);
            startActivity(intent);
        }

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomepageUser.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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

    private void getData(){
        DatabaseReference mDatabase = FirebaseDatabase
                .getInstance(Method.database_url)
                .getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/parents");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                // tambahkan code di sini untuk mengambil data
                name.setText(user.full_name.substring(0, 11)); // hanya 11 karakter agar tidak overflow
                country.setText(user.country);
                phone_number.setText(user.phone_number);
                address.setText(user.address);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // tambahkan code ketika data gagal diambil
            }
        });
    }
}