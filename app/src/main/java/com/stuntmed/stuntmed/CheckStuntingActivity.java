package com.stuntmed.stuntmed;

import androidx.appcompat.app.AppCompatActivity;

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

        ImageView backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

//        OrtEnvironment ortEnvironment;
//
//        private OrtSession createORTSession(OrtEnvironment ortEnvironment) {
//            try {
//                // Reading model bytes from the resource
//                InputStream is = getResources().openRawResource(R.raw.sklearn_model_with_runtime_opt_ort);
//                byte[] modelBytes = new byte[is.available()];
//                is.read(modelBytes);
//                is.close();
//
//                // Creating the ORT Session
//                return ortEnvironment.createSession(modelBytes);
//            } catch (IOException e) {
//                e.printStackTrace();
//                return null;  // Handle the exception as you deem fit
//            }
//        }

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