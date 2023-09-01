package com.stuntmed.stuntmed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.stuntmed.stuntmed.Databases.User;
import com.stuntmed.stuntmed.Profiles.EditProfileBaby;
import com.stuntmed.stuntmed.Profiles.EditProfileParents;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashScreen.this, HomepageUser.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}