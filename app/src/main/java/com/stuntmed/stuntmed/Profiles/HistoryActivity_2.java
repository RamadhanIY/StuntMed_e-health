package com.stuntmed.stuntmed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.stuntmed.stuntmed.R;

public class HistoryActivity_2 extends AppCompatActivity {


    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setSelectedItemId(R.id.button_history);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.button_home) {
                startActivity(new Intent(getApplicationContext(), HomepageUser.class ));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
            } else if (id == R.id.button_explore) {
                startActivity(new Intent(getApplicationContext(), ExplorerActivity.class ));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
            } else if (id == R.id.button_history) {
                return true;
            } else if (id == R.id.button_profile) {
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class ));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();
            } else {
                return false;
            }
            return false;


        });
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryActivity_2.this, CheckStuntingActivity.class);
                startActivity(intent);
            }
        });
    }

}