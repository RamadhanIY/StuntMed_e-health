package com.stuntmed.stuntmed;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import com.stuntmed.stuntmed.Databases.Baby;
import com.stuntmed.stuntmed.Databases.User;
import com.stuntmed.stuntmed.Homepage.ChildAdapter;
import com.stuntmed.stuntmed.Homepage.ChildModelClass;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.stuntmed.stuntmed.Registers.RegisterParents;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomepageUser extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView recyclerView;

    LinearLayout feature1, feature2;

    List<Baby> childModelClassList = new ArrayList<>();

    ArrayList<String> allNikList = new ArrayList<>();

    FloatingActionButton fab;

    View emptyLayout;

    TextView username;
    List<Baby> babies = new ArrayList<>();
    private ArrayList<String> listOfNik;

    CircleImageView image_profile;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_user_homepage);

        image_profile = findViewById(R.id.circleImageView);
        Method.updateProfileImage(image_profile);

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

        feature1 = findViewById(R.id.feature1);
        feature2 = findViewById(R.id.feature2);
        feature1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomepageUser.this, CheckStuntingListChild.class);
                startActivity(intent);
                finish();
            }
        });
        feature2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomepageUser.this, Imunisasi.class);
                startActivity(intent);
                finish();
            }
        });

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.button_home) {
                return true;
//            } else if (id == R.id.button_explore) {
//                startActivity(new Intent(getApplicationContext(), ExplorerActivity.class ));
//                overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
//                finish();
            } else if (id == R.id.button_history) {


                DatabaseReference mDatabase = FirebaseDatabase
                        .getInstance(Method.database_url)
                        .getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/babies");

                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        allNikList.clear(); // Bersihkan list sebelum memasukkan data baru
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {
                            String nik = childSnapshot.getKey();
                            allNikList.add(nik);
                        }
                        sendListOfNikToHistoryActivity(allNikList);


                        // Setelah mendapatkan semua nik, Anda bisa melakukan operasi lain,
                        // misalnya memperbarui UI atau mengirim list tersebut ke activity lain.
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e("FirebaseError", "Error fetching data: ", databaseError.toException());
                    }
                });
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
                Intent intent = new Intent(HomepageUser.this, CheckStuntingListChild.class);
                startActivity(intent);
                finish();
            }
        });
        try {
        getAllBabyNiks();
        checkDataAndDisplay(childModelClassList);
        getDataUser();
        }catch (Exception e){}

    }


    private void checkDataAndDisplay(List<?> dataList) {

        if (dataList.isEmpty()) {
            recyclerView.setVisibility(View.GONE);
            emptyLayout.setVisibility(View.VISIBLE);
        } else {
            ChildAdapter childAdapter;
            recyclerView.setVisibility(View.VISIBLE);


            childAdapter = new ChildAdapter(childModelClassList,HomepageUser.this);
            recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
            recyclerView.setAdapter(childAdapter);
            childAdapter.notifyDataSetChanged();
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
                HomepageUser.this.onItemsObtained(babies);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void onItemsObtained(List<Baby> babies) {
        this.babies = babies;
        for (Baby baby : babies) {

            childModelClassList.add(baby);
            Log.d("Ada nama bro",childModelClassList.get(0).name);

        }
        checkDataAndDisplay(childModelClassList);
    }

    private void getDataUser(){
        DatabaseReference mDatabase = FirebaseDatabase
                .getInstance(Method.database_url)
                .getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/parents");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                String name = getFirstName(user.full_name);
                username.setText(name);
                // Tampilkan nama ke dalam TextView atau widget lainnya

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // tambahkan code ketika data gagal diambil
            }
        });
    }

    public String getFirstName(String fullName) {
        return fullName.split(" ")[0];
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
    private void sendListOfNikToHistoryActivity(ArrayList<String> nikList) {
        Intent intent = new Intent(this, HistoryActivity_2.class);
        intent.putStringArrayListExtra("nikList", nikList);
        startActivity(intent);
    }


}