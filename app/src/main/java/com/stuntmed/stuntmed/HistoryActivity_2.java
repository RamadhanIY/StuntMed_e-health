package com.stuntmed.stuntmed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stuntmed.stuntmed.CheckStuntingActivity;
//import com.stuntmed.stuntmed.ExplorerActivity;
import com.stuntmed.stuntmed.Databases.Baby;
import com.stuntmed.stuntmed.Databases.BeratTinggiLKBulanan;
import com.stuntmed.stuntmed.HomepageUser;
import com.stuntmed.stuntmed.ProfileActivity;
import com.stuntmed.stuntmed.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity_2 extends AppCompatActivity {


    History_adapter history_adapter;

    private ArrayList<BeratTinggiLKBulanan> laporanpengecekan =new ArrayList<>();
    private ArrayList<BeratTinggiLKBulanan> laporanpengecekananak =new ArrayList<>();
    private ListView historyListView;
    private ArrayList<ListData_History> dataHistoryArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_2);

        historyListView = findViewById(R.id.ListView);

        // Contoh data (Anda dapat menggantinya dengan data asli Anda)
        dataHistoryArrayList = new ArrayList<>();
        dataHistoryArrayList.add(new ListData_History(R.drawable.profie_pictures, "Deskripsi 1","12-09-08","Tidak stunting","Anjay Mabar"));
        dataHistoryArrayList.add(new ListData_History(R.drawable.profie_pictures, "Deskripsi 2","12-09-08","Tidak stunting","Anjay Mabar"));

        ImageView backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Inisialisasi adapter dan set ke ListView
        History_adapter adapter = new History_adapter(this, dataHistoryArrayList);
        historyListView.setAdapter(adapter);
        getAllBabyNiks();

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, HomepageUser.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
    private void getAllBabyNiks(){
        DatabaseReference mDatabase = FirebaseDatabase
                .getInstance(Method.database_url)
                .getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/babies");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> niks = new ArrayList<>();
                for (DataSnapshot babySnapshot : snapshot.getChildren()) {
                    // Ambil nik dari key child
                    String nik = babySnapshot.getKey();
                    niks.add(nik);
                }

                // Dari sini, Anda bisa memanfaatkan list niks sesuai kebutuhan Anda
                // Misalnya mencetak semua niks
                 for (String nik : niks) {
                                    getDataBabyByNik(nik);
                                    Log.d("Ada nik bro",nik);
                                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // tambahkan code ketika data gagal diambil
            }
        });
    }

    private void getDataBabyByNik(final String nik){
        DatabaseReference mDatabase = FirebaseDatabase
                .getInstance(Method.database_url)
                .getReference("databulanan/"  + nik );

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot babySnapshot : snapshot.getChildren()) {
                    BeratTinggiLKBulanan baby = babySnapshot.getValue(BeratTinggiLKBulanan.class);
                    laporanpengecekananak.add(baby);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("FirebaseError", "Error fetching data: ", error.toException());
            }
        });
    }
//    public void onItemsObtained(laporanpengecekananak) {
//
//        laporanpengecekan.add(laporanpengecekananak);
//
//    }


}