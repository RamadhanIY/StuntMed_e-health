package com.stuntmed.stuntmed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.stuntmed.stuntmed.R;
import com.stuntmed.stuntmed.Explorer.ChildModelClass;
import com.stuntmed.stuntmed.Explorer.ChildModelClassPost;
import com.stuntmed.stuntmed.Explorer.ExplorerActivity2;
import com.stuntmed.stuntmed.Explorer.ParentAdapter;
import com.stuntmed.stuntmed.Explorer.ParentAdapter2;
import com.stuntmed.stuntmed.Explorer.ParentAdapter3;
import com.stuntmed.stuntmed.Explorer.ParentModelClass;
import com.stuntmed.stuntmed.Explorer.ParentModelClassPost;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class ExplorerActivity extends AppCompatActivity implements ExplorerActivity2 {

    RecyclerView recyclerView;
    ArrayList<ParentModelClass> parentModelClassArrayList;
    ArrayList<ChildModelClass> childModelClassArrayList;
    ArrayList<ChildModelClass> trend1Lists;
    ArrayList<ChildModelClass> trend2Lists;

    ArrayList<ChildModelClass> trend3Lists;

    ArrayList<ParentModelClassPost> parentModelClassPosts;
    ArrayList<ChildModelClassPost> childModelClassPostArrayList;
    ArrayList<ChildModelClassPost> postbyuser1;

    ParentAdapter parentAdapter;
    ParentAdapter2 parentAdapter2;

    ParentAdapter3 parentAdapter3;
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
        trends1();
//        trends2();
//        post_by_user();
//        trends3();

    }

    protected void trends1(){
        recyclerView = findViewById(R.id.rv_parents);
        childModelClassArrayList = new ArrayList<>();
        trend1Lists = new ArrayList<>();
        trend2Lists = new ArrayList<>();
        trend3Lists = new ArrayList<>();
        parentModelClassArrayList =  new ArrayList<>();
        trend1Lists.add(new ChildModelClass(R.drawable.threads1,"test1"));
        trend1Lists.add(new ChildModelClass(R.drawable.threads1,"test2"));
        trend1Lists.add(new ChildModelClass(R.drawable.threads1,"test3"));
        trend1Lists.add(new ChildModelClass(R.drawable.threads1,"test4"));

        parentModelClassArrayList.add(new ParentModelClass("Trending Topics",trend1Lists));

        trend2Lists.add(new ChildModelClass(R.drawable.threads1,"test1"));
        trend2Lists.add(new ChildModelClass(R.drawable.threads1,"test2"));
        trend2Lists.add(new ChildModelClass(R.drawable.threads1,"test3"));
        trend2Lists.add(new ChildModelClass(R.drawable.threads1,"test4"));

        parentModelClassArrayList.add(new ParentModelClass("Trending Topics",trend2Lists));

        trend3Lists.add(new ChildModelClass(R.drawable.threads1,"test1"));
        trend3Lists.add(new ChildModelClass(R.drawable.threads1,"test2"));
        trend3Lists.add(new ChildModelClass(R.drawable.threads1,"test3"));
        trend3Lists.add(new ChildModelClass(R.drawable.threads1,"test4"));

        parentModelClassArrayList.add(new ParentModelClass("Trending Topics",trend3Lists));

        parentAdapter = new ParentAdapter(parentModelClassArrayList,ExplorerActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(parentAdapter);

        parentAdapter.notifyDataSetChanged();
    }

    protected void trends2(){

        recyclerView = findViewById(R.id.rv_parents);
        childModelClassArrayList = new ArrayList<>();
        trend1Lists = new ArrayList<>();
        parentModelClassArrayList =  new ArrayList<>();

        trend1Lists.add(new ChildModelClass(R.drawable.threads1,"test1"));
        trend1Lists.add(new ChildModelClass(R.drawable.threads1,"test2"));
        trend1Lists.add(new ChildModelClass(R.drawable.threads1,"test3"));
        trend1Lists.add(new ChildModelClass(R.drawable.threads1,"test4"));

        parentModelClassArrayList.add(new ParentModelClass("Search by Category",trend1Lists));


        parentAdapter2 = new ParentAdapter2(parentModelClassArrayList,ExplorerActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(parentAdapter2);

        parentAdapter2.notifyDataSetChanged();
    }

    protected void post_by_user(){

        recyclerView = findViewById(R.id.rv_parents);
        childModelClassPostArrayList = new ArrayList<>();
        postbyuser1 = new ArrayList<>();
        parentModelClassPosts =  new ArrayList<>();

        postbyuser1.add(new ChildModelClassPost(R.drawable.threads1,"test1","Mantap anak2"));
        postbyuser1.add(new ChildModelClassPost(R.drawable.threads1,"test2","Mantap anak2"));
        postbyuser1.add(new ChildModelClassPost(R.drawable.threads1,"test3","Mantap anak2"));
        postbyuser1.add(new ChildModelClassPost(R.drawable.threads1,"test4","Mantap anak2"));

        parentModelClassPosts.add(new ParentModelClassPost("More information",postbyuser1));


        parentAdapter3 = new ParentAdapter3(parentModelClassPosts,ExplorerActivity.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(parentAdapter3);

        parentAdapter3.notifyDataSetChanged();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
}

