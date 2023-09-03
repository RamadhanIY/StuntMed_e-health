package com.stuntmed.stuntmed.Databases;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stuntmed.stuntmed.Method;

import java.util.HashMap;
import java.util.Map;

public class Baby {
    public String nik;
    public String name;
    public String date_of_birth;
    public String country;
    public String gender;
    public String berat;
    public  String tinggi;
    public String lk;
    public String kategori;

    public String uri;
    private static Baby baby;


    public Baby(){

    }

    public Baby(String uri,String nik, String name, String date_of_birth, String country, String gender, String berat, String tinggi, String lk, String kategori){
        this.uri = uri;
        this.nik = nik;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.country = country;
        this.gender = gender;
        this.berat = berat;
        this.tinggi = tinggi;
        this.lk = lk;
        this.kategori = kategori;
    }

    public static void writeNewBaby(String uri,String nik, String name, String date_of_birth, String country, String gender, String berat, String tinggi, String lk,String kategori) {
        FirebaseUser current_user = Method.getCurrentUser();
        Baby baby = new Baby(uri,nik, name, date_of_birth,country,gender,berat,tinggi,lk,kategori);

        Method.setValueOnDatabase("Users/"+current_user.getUid()+"/babies/"+nik,
                                    baby);

    }

    private static void setBaby(Baby baby) {
        Baby.baby = baby;
    }

    private static void updateBaby(String nik){
        DatabaseReference mDatabase = Method.getDatabaseReference("Users/"+Method.getCurrentUser().getUid()+"/babies/"+nik);

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                Method.setObj(snapshot.getValue(Baby.class));
                setBaby(snapshot.getValue(Baby.class));
                Log.d("testttttasdasd", "berhasil");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.d("testttttasdasd", "gagal")       ;

            }
        });
    }

    public static Baby getData(String nik){
        updateBaby(nik);
        return Baby.baby;
    };

    public String getNik() {
        return nik;
    }

    // Jika Anda belum memiliki setter, tambahkan juga:
    public void setNik(String nik) {
        this.nik = nik;
    }

//    private static String checkNik(String nik){
//        return .child();
//    }
}
