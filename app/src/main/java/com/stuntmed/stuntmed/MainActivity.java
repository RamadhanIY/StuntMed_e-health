package com.stuntmed.stuntmed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.stuntmed.stuntmed.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.stuntmed.stuntmed.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    FirebaseAuth firebaseAuth;
    GoogleSignInClient googleSignInClient;
    TextView name, email;
    Button signOutBtn;
    private Button grafikBtn;

    ActivityMainBinding binding;
    AddChild_Adapter addChildAdapter;
    ArrayList<DataAddChild> dataAddChildArrayList = new ArrayList<>();
    DataAddChild dataAddChild;
    private com.stuntmed.stuntmed.AddChild_Adapter AddChild_Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        //Add Child
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        int[] photo = {R.drawable.histiry_picture}
//        String[] NameChild = {R.string.name_child};
//        String[] umur = {R.string.umur_bayi};
//
//        DataAddChild = new DataAddChild(NameChild[0], umur[0]);
//        dataAddChildArrayList.add(dataAddChild);

        // Assign variable
        name = findViewById(R.id.nameText);
        email = findViewById(R.id.email);
        signOutBtn = findViewById(R.id.signOutBtn);
        grafikBtn = findViewById(R.id.btnGrafik);

        // Initialize firebase auth
        firebaseAuth = FirebaseAuth.getInstance();

        // Initialize firebase user
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        // Check condition
        if (firebaseUser != null) {
            // set name on text view
            name.setText(firebaseUser.getDisplayName());
            // set email on text view
            email.setText(firebaseUser.getEmail());
        }

        // Initialize sign in client
        googleSignInClient = GoogleSignIn.getClient(MainActivity.this, GoogleSignInOptions.DEFAULT_SIGN_IN);

        signOutBtn.setOnClickListener(view -> {
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


        grafikBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, grafik.class);
                startActivity(intent);

            }
        });
    }

}