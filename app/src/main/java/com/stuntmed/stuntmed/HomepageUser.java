package com.stuntmed.stuntmed;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.stuntmed.stuntmed.Databases.Baby;
import com.stuntmed.stuntmed.Databases.User;
import com.stuntmed.stuntmed.Homepage.ChildAdapter;
import com.stuntmed.stuntmed.Homepage.ChildModelClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import java.util.ArrayList;
import java.util.List;


public class HomepageUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;

    List<ChildModelClass> childModelClassList = new ArrayList<>();

    ChildAdapter childAdapter;

    FloatingActionButton fab;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_homepage);

//        User.writeNewUser("benngki", "Benediktus Hengki Setiawan", "benediktushengkisetiawan@gmail.com", "laki-laki", "Bengkayang", "Indonesia", "081257522018", "6107041313130004", "01-01-1000");
//        User.writeNewUser();
        Baby.writeNewBaby("123","rama","7 juli 2003","Indo","Lalaki","10.00","70.00","30.00");

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

        add_babies();

    }

    protected void add_babies(){
        recyclerView = findViewById(R.id.baby_profiles);
        childModelClassList.add(new ChildModelClass(R.drawable.image2,"Yanto",R.drawable.woman_gender,"Tidak Stunting",R.drawable.baby_icons,"10","19","20"));
        childModelClassList.add(new ChildModelClass(R.drawable.image2,"Yanto",R.drawable.woman_gender,"Tidak Stunting",R.drawable.baby_icons,"10","19","20"));

//        ListBabies.add(new ChildModelClass(R.drawable.image2,"Yanto",R.drawable.woman_gender,"Tidak Stunting",R.drawable.baby_icons,"10","19","20"));
        childAdapter = new ChildAdapter(childModelClassList,HomepageUser.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(childAdapter);
        childAdapter.notifyDataSetChanged();



    }



    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

}
