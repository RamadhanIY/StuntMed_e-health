package com.stuntmed.stuntmed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.stuntmed.stuntmed.CheckStuntingActivity;
import com.stuntmed.stuntmed.ExplorerActivity;
import com.stuntmed.stuntmed.HomepageUser;
import com.stuntmed.stuntmed.ProfileActivity;
import com.stuntmed.stuntmed.R;

import java.util.ArrayList;

public class HistoryActivity_2 extends AppCompatActivity {


    History_adapter history_adapter;
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

        // Inisialisasi adapter dan set ke ListView
        History_adapter adapter = new History_adapter(this, dataHistoryArrayList);
        historyListView.setAdapter(adapter);





    }

}