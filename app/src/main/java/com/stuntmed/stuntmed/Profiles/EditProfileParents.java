package com.stuntmed.stuntmed.Profiles;

import static com.stuntmed.stuntmed.Databases.User.writeNewUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Tag;
import com.stuntmed.stuntmed.Databases.User;
import com.stuntmed.stuntmed.HomepageUser;
import com.stuntmed.stuntmed.Method;
import com.stuntmed.stuntmed.R;
import com.stuntmed.stuntmed.RegisterParents;

public class EditProfileParents extends AppCompatActivity {

    TextInputEditText inputfullnama, inputnik, inputemail, inputphonenumber,inputcountry,inputgender,inputaddress;
    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance(Method.database_url).getReference();
    private static final String TAG = "EditProfileParents";


    @Override
    protected void onCreate(Bundle savedInstanceState) {




        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_parents);

        inputnik = findViewById(R.id.edit_NIK_parents);
        inputfullnama = findViewById(R.id.edit_fullname_parents);
        inputemail = findViewById(R.id.edit_email);
        inputphonenumber = findViewById(R.id.edit_phonenumber);
        inputcountry = findViewById(R.id.edit_country);
        inputgender = findViewById(R.id.edit_gender);
        inputaddress = findViewById(R.id.edit_address);

        Button submitButton = (Button) findViewById(R.id.button_submit);
        ImageView backButton = (ImageView) findViewById(R.id.backButton_profiles);




        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        submitButton.setOnClickListener(view -> {
            writeNewUser(null,inputfullnama.getText().toString(),inputemail.getText().toString(),inputgender.getText().toString(),inputaddress.getText().toString(),inputcountry.getText().toString(),inputphonenumber.getText().toString(),inputnik.getText().toString(),null);
            Intent intent = new Intent(this, HomepageUser.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomepageUser.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}