package com.stuntmed.stuntmed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.stuntmed.stuntmed.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        User.writeNewUser("benngki", "Benediktus Hengki Setiawan", "benediktushengkisetiawan@gmail.com", "laki-laki", "Bengkayang", "Indonesia", "081257522018", "6107041313130004", "01-01-1000");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(SplashScreen.this, SignIn.class);
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}