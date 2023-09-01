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
    public double berat;
    public  double tinggi;
    public double lk;
    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance(Method.database_url).getReference();

    public Baby(){

    }

    public Baby(String nik, String name, String date_of_birth, String country, String gender, double berat, double tinggi, double lk){
        this.nik = nik;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.country = country;
        this.gender = gender;
        this.berat = berat;
        this.tinggi = tinggi;
        this.lk = lk;
    }

    public static void writeNewBaby(String nik, String name, String date_of_birth, String country, String gender, double berat, double tinggi, double lk) {

        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
        Baby baby = new Baby(nik, name, date_of_birth,country,gender,berat,tinggi,lk);

        mDatabase.child("Users").child(current_user.getUid()).child("babies").child(nik).child("dataset").setValue(baby);
    }

}