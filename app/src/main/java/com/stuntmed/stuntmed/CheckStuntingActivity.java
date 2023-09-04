package com.stuntmed.stuntmed;

import static com.stuntmed.stuntmed.Databases.BeratTinggiLK.writenewBeratTinggiLK;
import static com.stuntmed.stuntmed.Databases.BeratTinggiLKBulanan.writenewBeratTinggiLKBulanan;
import static com.stuntmed.stuntmed.DateHelper.getTodayDate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputEditText;
import com.stuntmed.stuntmed.Databases.Baby;

import java.io.IOException;
import java.io.InputStream;

import ai.onnxruntime.OrtSession;
import ai.onnxruntime.OrtEnvironment;

public class CheckStuntingActivity extends AppCompatActivity {

    EditText Et;
    TextView tv;
    ImageView iv;

    TextInputEditText berat,tinggi,lk;
    String weight_label, height_label, hc_label, NIK, tanggalpengisian;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkstunting);

        berat = findViewById(R.id.input_berat);
        tinggi = findViewById(R.id.input_tinggi);
        lk = findViewById(R.id.input_lingkarkepala);
        tanggalpengisian = getTodayDate();

        NIK = getIntent().getStringExtra("NIK");

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
                Baby.getBabyByNik(NIK, new Method.VolleyCallback() {
                    @Override
                    public void onSuccess(Object result) {
                        Baby baby = (Baby) result;
                        Integer umurI = AgeCalculator.calculateAgeInMonths(baby.date_of_birth);
                        String umur = umurI.toString();

                        Method.predict_height(CheckStuntingActivity.this,
                                Method.convertgender(baby.gender), umur,
                                tinggi.getText().toString(), new Method.VolleyCallback() {
                                    @Override
                                    public void onSuccess(Object result) {
                                        height_label = (String) result;

                                        Method.predict_weight(CheckStuntingActivity.this,
                                                Method.convertgender(baby.gender), umur,
                                                berat.getText().toString(), new Method.VolleyCallback() {
                                                    @Override
                                                    public void onSuccess(Object result) {
                                                        weight_label = (String) result;

                                                        Method.predict_hc(CheckStuntingActivity.this,
                                                                Method.convertgender(baby.gender), umur,
                                                                lk.getText().toString(), new Method.VolleyCallback() {
                                                                    @Override
                                                                    public void onSuccess(Object result) {
                                                                        hc_label = (String) result;

                                                                        updateData();
                                                                    }

                                                                    @Override
                                                                    public void onError(Object error) {

                                                                    }
                                                                });
                                                    }

                                                    @Override
                                                    public void onError(Object error) {

                                                    }
                                                });
                                    }

                                    @Override
                                    public void onError(Object error) {

                                    }
                                });
                    }

                    @Override
                    public void onError(Object error) {

                    }
                });
            }
        });



        // Add other methods or members of the class below
    }

    private void  updateData(){
        writenewBeratTinggiLK(NIK,berat.getText().toString(),tinggi.getText().toString(),lk.getText().toString(),
                height_label,weight_label,
                hc_label,
                new Check_Stunting(height_label, weight_label, hc_label)
                        .cekStunting()
        );
        writenewBeratTinggiLKBulanan(tanggalpengisian,NIK,berat.getText().toString(),tinggi.getText().toString(),lk.getText().toString(),height_label,weight_label,
                hc_label,
                new Check_Stunting(height_label, weight_label, hc_label)
                        .cekStunting());

        Intent intent = new Intent(CheckStuntingActivity.this, HasilStuntingActivity.class);
        intent.putExtra("NIK", NIK); // Asumsi DataAddChild memiliki method getNik()
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomepageUser.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}