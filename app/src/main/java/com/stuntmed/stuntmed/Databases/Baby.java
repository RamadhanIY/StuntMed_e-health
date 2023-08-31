package com.stuntmed.stuntmed.Databases;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.stuntmed.stuntmed.Method;

public class Baby {
    public String nik;
    public String name;
    public String date_of_birth;
    public String country;
    public String gender;
    public String address;

    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance(Method.database_url).getReference();

    public Baby(){

    }

    public Baby(String nik, String name, String date_of_birth, String country, String gender, String address){
        this.nik = nik;
        this.name = name;
        this.date_of_birth = date_of_birth;
        this.country = country;
        this.gender = gender;
        this.address = address;
    }

    public static void writeNewUser(String username, String full_name, String email, String gender, String address, String country, String phone_number, String nik, String date_of_birth) {
        User user = new User(username, full_name, email, gender, address, country, phone_number, nik, date_of_birth);

        mDatabase.child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user);
    }


}
