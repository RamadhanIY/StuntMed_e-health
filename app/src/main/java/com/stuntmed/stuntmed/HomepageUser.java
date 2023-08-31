package com.stuntmed.stuntmed;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.stuntmed.stuntmed.Databases.User;
import com.stuntmed.stuntmed.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;


public class HomepageUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FloatingActionButton fab;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_homepage);

        User.writeNewUser("benngki", "Benediktus Hengki Setiawan", "benediktushengkisetiawan@gmail.com", "laki-laki", "Bengkayang", "Indonesia", "081257522018", "6107041313130004", "01-01-1000");

        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        //ArrayList buat nyimpen foto (sementara, blm pake database)

        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.image1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image2, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        //Bottom Navbar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setSelectedItemId(R.id.button_home);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.button_home) {
                return true;
            } else if (id == R.id.button_explore) {
                startActivity(new Intent(getApplicationContext(), ExplorerActivity.class ));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();
            } else if (id == R.id.button_history) {
                startActivity(new Intent(getApplicationContext(), HistoryActivity.class ));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();
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
                Intent intent = new Intent(HomepageUser.this, CheckStuntingActivity.class);
                startActivity(intent);
            }
        });

    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

}
