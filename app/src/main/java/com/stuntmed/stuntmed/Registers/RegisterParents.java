package com.stuntmed.stuntmed.Registers;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.stuntmed.stuntmed.HomepageUser;
import com.stuntmed.stuntmed.R;
import com.stuntmed.stuntmed.Register;

public class RegisterParents extends AppCompatActivity{

    String inputfullnama, inputnik, inputemail, inputphonenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_parents);

        ImageView backButton = (ImageView) findViewById(R.id.backButton_profiles);
        Button submitbutton = (Button) findViewById(R.id.button_submit);

//        AutoCompleteTextView dropdown = findViewById(R.id.edit_country);
//
//        String[] opsi = new String[]{"Indonesia", "Amerika", "Jepang"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                this,
//                R.layout.activity_edit_profile_parents,
//                opsi
//        );
//        dropdown.setAdapter(adapter);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterParents.this, HomepageUser.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }


//    public void dropdowncountry(){
//        AutoCompleteTextView dropdown = findViewById(R.id.edit_country);
//
//        String[] opsi = new String[]{"Indonesia", "Amerika", "Jepang"};
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(
//                this,
//                R.layout.activity_edit_profile_parents,
//                opsi
//        );
//        dropdown.setAdapter(adapter);
//    }
}
