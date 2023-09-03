package com.stuntmed.stuntmed;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen_UpdateSuccess_Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_success_page);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashScreen_UpdateSuccess_Register.this, HomepageUser.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}