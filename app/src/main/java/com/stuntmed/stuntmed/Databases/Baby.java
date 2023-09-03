package com.stuntmed.stuntmed.Databases;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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

    public String uri;
    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance(Method.database_url).getReference();

    public Baby(){

    }

    public Baby(String uri,String nik, String name, String date_of_birth, String country, String gender, String berat, String tinggi, String lk){
        this.uri = uri;
        this.nik = nik;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.country = country;
        this.gender = gender;
        this.berat = berat;
        this.tinggi = tinggi;
        this.lk = lk;
    }

    public static void writeNewBaby(String uri,String nik, String name, String date_of_birth, String country, String gender, String berat, String tinggi, String lk) {

        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
        Baby baby = new Baby(uri,nik, name, date_of_birth,country,gender,berat,tinggi,lk);

        mDatabase.child("Users").child(current_user.getUid()).child("babies").child(nik).child("dataset").setValue(baby);
    }

}
