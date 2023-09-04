package com.stuntmed.stuntmed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.stuntmed.stuntmed.Profiles.EditProfileParents;

import java.util.ArrayList;

public class Imunisasi extends AppCompatActivity {

//    Imunisasi_Adapater adapater;
    ListView listView;

    ArrayList<ListData_Imunisasi> listData_imunisasis = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imunisasi);
        ImageView backButton = (ImageView) findViewById(R.id.backButton);
        listView = findViewById(R.id.ListView);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        listData_imunisasis.add(new ListData_Imunisasi("0 Bulan", "Hepatitis B", "Dosis Pertama (segera setelah lahir)"));
        listData_imunisasis.add(new ListData_Imunisasi("1 Bulan", "Hepatitis B", "Dosis Kedua"));
        listData_imunisasis.add(new ListData_Imunisasi("2 Bulan", "DTP/HB-Hib", "Dosis Pertama (Difteri, Tetanus, Pertusis, Hepatitis B, Haemophilus influenzae tipe b)"));
        listData_imunisasis.add(new ListData_Imunisasi("3 Bulan", "Polio", "Dosis Pertama"));
        listData_imunisasis.add(new ListData_Imunisasi("4 Bulan", "DTP/HB-Hib", "Dosis Kedua"));
        listData_imunisasis.add(new ListData_Imunisasi("5 Bulan", "Polio", "Dosis Kedua"));
        listData_imunisasis.add(new ListData_Imunisasi("6 Bulan", "Hepatitis B", "Dosis Ketiga"));
        listData_imunisasis.add(new ListData_Imunisasi("9 Bulan", "Campak & Rubella", "Satu Dosis"));
        listData_imunisasis.add(new ListData_Imunisasi("12 Bulan", "DTP/HB-Hib", "Dosis Ketiga"));
        Imunisasi_Adapater adapter = new Imunisasi_Adapater(Imunisasi.this, listData_imunisasis);
        listView.setAdapter(adapter);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Imunisasi.this, ProfileActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }



}