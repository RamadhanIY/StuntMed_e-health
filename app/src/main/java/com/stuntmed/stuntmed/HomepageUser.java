package com.stuntmed.stuntmed;


import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stuntmed.stuntmed.Databases.Baby;
import com.stuntmed.stuntmed.Databases.User;
import com.stuntmed.stuntmed.Homepage.ChildAdapter;
import com.stuntmed.stuntmed.Homepage.ChildModelClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.stuntmed.stuntmed.Registers.RegisterParents;

import java.util.ArrayList;
import java.util.List;


public class HomepageUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;

    List<ChildModelClass> childModelClassList = new ArrayList<>();

    ChildAdapter childAdapter;

    FloatingActionButton fab;

    View emptyLayout;

    TextView username;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_homepage);

//        User.writeNewUser("benngki", "Benediktus Hengki Setiawan", "benediktushengkisetiawan@gmail.com", "laki-laki", "Bengkayang", "Indonesia", "081257522018", "6107041313130004", "01-01-1000");
//        User.writeNewUser();

        ImageSlider imageSlider = findViewById(R.id.imageSlider);
        username = findViewById(R.id.nameuser_homepage);

        //ArrayList buat nyimpen foto (sementara, blm pake database)

        ArrayList<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.image1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image2, ScaleTypes.FIT));

        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
        recyclerView = findViewById(R.id.baby_profiles);
        emptyLayout = findViewById(R.id.empty_layout);


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
                startActivity(new Intent(getApplicationContext(), HistoryActivity_2.class ));
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
        childModelClassList.add(new ChildModelClass(R.drawable.image2,"Yanto",R.drawable.woman_gender,"Tidak Stunting",R.drawable.baby_icons,"10","19","20"));
        childModelClassList.add(new ChildModelClass(R.drawable.image2,"Yanto",R.drawable.woman_gender,"Tidak Stunting",R.drawable.baby_icons,"10","19","20"));
        add_babies();
        getDataUser();
    }

    protected void add_babies(){
        checkDataAndDisplay(childModelClassList);


//        ListBabies.add(new ChildModelClass(R.drawable.image2,"Yanto",R.drawable.woman_gender,"Tidak Stunting",R.drawable.baby_icons,"10","19","20"));
        childAdapter = new ChildAdapter(childModelClassList,HomepageUser.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView.setAdapter(childAdapter);
        childAdapter.notifyDataSetChanged();



    }
    private void checkDataAndDisplay(List<?> dataList) {
        if (dataList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyLayout.setVisibility(View.VISIBLE);
        } else {
            recyclerView.setVisibility(View.VISIBLE);
            emptyLayout.setVisibility(View.GONE);
        }
    }


    private void getDataUser(){
        DatabaseReference mDatabase = FirebaseDatabase
                .getInstance(Method.database_url)
                .getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/parents");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                String name = user.full_name;
                username.setText(name);
                // Tampilkan nama ke dalam TextView atau widget lainnya

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // tambahkan code ketika data gagal diambil
            }
        });
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }

}