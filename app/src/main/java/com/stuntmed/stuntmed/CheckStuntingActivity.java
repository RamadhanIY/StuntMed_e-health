package com.stuntmed.stuntmed;

import static com.stuntmed.stuntmed.Databases.BeratTinggiLK.writenewBeratTinggiLK;
import static com.stuntmed.stuntmed.Databases.BeratTinggiLKBulanan.writenewBeratTinggiLKBulanan;
import static com.stuntmed.stuntmed.DateHelper.getTodayDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.android.material.textfield.TextInputEditText;

import java.io.IOException;
import java.io.InputStream;

import ai.onnxruntime.OrtSession;
import ai.onnxruntime.OrtEnvironment;

public class CheckStuntingActivity extends AppCompatActivity {

    EditText Et;
    TextView tv;
    ImageView iv;

    TextInputEditText berat,tinggi,lk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkstunting);

        berat = findViewById(R.id.input_berat);
        tinggi = findViewById(R.id.input_tinggi);
        lk = findViewById(R.id.input_lingkarkepala);
        String tanggalpengisian = getTodayDate();

        String NIK = getIntent().getStringExtra("NIK");

        AppCompatButton submitButton = (AppCompatButton) findViewById(R.id.button_submit);
        ImageView backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writenewBeratTinggiLK(NIK,berat.getText().toString(),tinggi.getText().toString(),lk.getText().toString());
                writenewBeratTinggiLKBulanan(tanggalpengisian,NIK,berat.getText().toString(),tinggi.getText().toString(),lk.getText().toString(),"test","test12","test123");
                Intent intent = new Intent(CheckStuntingActivity.this, HomepageUser.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            }
        });



        // Add other methods or members of the class below
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomepageUser.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}