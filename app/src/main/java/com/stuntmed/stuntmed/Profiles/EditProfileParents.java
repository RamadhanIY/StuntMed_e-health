package com.stuntmed.stuntmed.Profiles;

import static com.stuntmed.stuntmed.Databases.User.writeNewUser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.stuntmed.stuntmed.HomepageUser;
import com.stuntmed.stuntmed.Method;
import com.stuntmed.stuntmed.R;

public class EditProfileParents extends AppCompatActivity {

    TextInputEditText inputfullnama, inputnik, inputemail, inputphonenumber,inputaddress;
    AutoCompleteTextView inputcountry,inputgender;

    String[] country = {"Indonesia, Amerika, Jepang"};
    String[] gender = {"Laki-laki","Perempuan"};

    ArrayAdapter<String> adaptercountry, adaptergender;

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

        adaptercountry = new ArrayAdapter<>(this,R.layout.list_country,country);
        adaptergender = new ArrayAdapter<>(this,R.layout.list_gender,gender);

        Button submitButton = (Button) findViewById(R.id.button_submit);
        ImageView backButton = (ImageView) findViewById(R.id.backButton_profiles);

        inputcountry.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String country = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(),"Country" + country,Toast.LENGTH_SHORT).show();
            }
        });


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