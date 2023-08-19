package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.myapplication.Explorer.ChildModelClass;
import com.example.myapplication.Explorer.ExplorerActivity2;
import com.example.myapplication.Explorer.ParentAdapter;
import com.example.myapplication.Explorer.ParentAdapter2;
import com.example.myapplication.Explorer.ParentModelClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ExplorerActivity extends AppCompatActivity implements ExplorerActivity2 {

    RecyclerView recyclerView;
    ArrayList<ParentModelClass> parentModelClassArrayList;
    ArrayList<ChildModelClass> childModelClassArrayList;
    ArrayList<ChildModelClass> trend1Lists;
    ArrayList<ChildModelClass> trend2Lists;

    ParentAdapter parentAdapter;
    ParentAdapter2 parentAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explorer);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavView);
        bottomNavigationView.setSelectedItemId(R.id.button_explore);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.button_explore) {
                return true;}
            if (id == R.id.button_home) {
                startActivity(new Intent(getApplicationContext(), HomepageUser.class ));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
                finish();
            } else if (id == R.id.button_history) {
                startActivity(new Intent(getApplicationContext(), HistoryActivity.class ));
                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                finish();
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
        trend1Lists.add(new ChildModelClass(R.drawable.threads1,"test1"));
        trend1Lists.add(new ChildModelClass(R.drawable.threads1,"test2"));
        trend1Lists.add(new ChildModelClass(R.drawable.threads1,"test3"));
        trend1Lists.add(new ChildModelClass(R.drawable.threads1,"test4"));

        parentModelClassArrayList.add(new ParentModelClass("Trending Topics",trend1Lists));


        parentAdapter = new ParentAdapter(parentModelClassArrayList,ExplorerActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(parentAdapter);

        parentAdapter.notifyDataSetChanged();
//        trends1();
//        trends2();

    }

//    protected void trends1(){
//        trend1Lists.add(new ChildModelClass(R.drawable.threads1,"test1"));
//        trend1Lists.add(new ChildModelClass(R.drawable.threads1,"test2"));
//        trend1Lists.add(new ChildModelClass(R.drawable.threads1,"test3"));
//        trend1Lists.add(new ChildModelClass(R.drawable.threads1,"test4"));
//
//        parentModelClassArrayList.add(new ParentModelClass("Trending Topics",trend1Lists));
//
//
//        parentAdapter = new ParentAdapter(parentModelClassArrayList,ExplorerActivity.this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(parentAdapter);
//
//        parentAdapter.notifyDataSetChanged();
//    }
//
//    protected void trends2(){
//
//        recyclerView = findViewById(R.id.rv_parents);
//        childModelClassArrayList = new ArrayList<>();
//        trend2Lists = new ArrayList<>();
//        parentModelClassArrayList =  new ArrayList<>();
//
//        trend2Lists.add(new ChildModelClass(R.drawable.threads1,"test1"));
//        trend2Lists.add(new ChildModelClass(R.drawable.threads1,"test2"));
//        trend2Lists.add(new ChildModelClass(R.drawable.threads1,"test3"));
//        trend2Lists.add(new ChildModelClass(R.drawable.threads1,"test4"));
//
//        parentModelClassArrayList.add(new ParentModelClass("Trending Topics",trend2Lists));
//
//
//        parentAdapter2 = new ParentAdapter2(parentModelClassArrayList,ExplorerActivity.this);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setAdapter(parentAdapter2);
//
//        parentAdapter.notifyDataSetChanged();
//    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
}

