package com.stuntmed.stuntmed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jakewharton.threetenabp.AndroidThreeTen;
import com.stuntmed.stuntmed.Databases.Baby;
import com.stuntmed.stuntmed.Homepage.ChildAdapter;
import com.stuntmed.stuntmed.Profiles.EditProfileBaby;
import com.stuntmed.stuntmed.Registers.RegisterBaby;

import java.util.ArrayList;
import java.util.List;

public class CheckStuntingListChild extends AppCompatActivity {

    AddChild_AdapterCheckStunting addChild_adapter;
    ArrayList<DataAddChild> listnamedatebaby = new ArrayList<>();
    List<Baby> babies = new ArrayList<>();

    ListView listView ;

    View emptyLayout;

    Button addbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_child);
        AndroidThreeTen.init(this);

        listView = findViewById(R.id.ListView);
        emptyLayout = findViewById(R.id.empty_layout);
        addbutton =  findViewById(R.id.AddButton);

        getAllBabyNiks();
        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckStuntingListChild.this,RegisterBaby.class);
                CheckStuntingListChild.this.startActivity(intent);
            }
        });
        ImageView backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



    }
    private void checkDataAndDisplay(List<?> dataList) {

        if (dataList.isEmpty()) {
            listView.setVisibility(View.GONE);
            emptyLayout.setVisibility(View.VISIBLE);
        } else {
            listView.setVisibility(View.VISIBLE);
            addChild_adapter = new AddChild_AdapterCheckStunting(this, listnamedatebaby);
            listView.setAdapter(addChild_adapter);
            addChild_adapter.notifyDataSetChanged();
            emptyLayout.setVisibility(View.GONE);
        }
    }



    private void getAllBabyNiks(){
        DatabaseReference mDatabase = FirebaseDatabase
                .getInstance(Method.database_url)
                .getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/babies");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                babies = new ArrayList<>();

                for (DataSnapshot babySnapshot : snapshot.getChildren()) {
                    Baby baby = babySnapshot.getValue(Baby.class);
                    babies.add(baby);



                }
                CheckStuntingListChild.this.onItemsObtained(babies);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void onItemsObtained(List<Baby> babies) {
        this.babies = babies;
        for (Baby baby : babies) {
            listnamedatebaby.add(new DataAddChild(baby.name,AgeCalculator.calculateAgeInMonthsAndDays(baby.date_of_birth),baby.nik));
            Log.d("Ada tanggal lahir bro",baby.date_of_birth);
        }
        checkDataAndDisplay(listnamedatebaby);

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}