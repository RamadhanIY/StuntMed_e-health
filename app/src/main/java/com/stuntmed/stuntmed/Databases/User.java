package com.stuntmed.stuntmed.Databases;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.stuntmed.stuntmed.Method;

public class User {
    public String username;
    public String email;
    public String gender;
    public String address;
    public String country;
    public  String phone_number;
    public String nik;
    public String full_name;
    public String date_of_birth; // dd-mm-yyyy

    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance(Method.database_url).getReference();


    public User(){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String full_name, String email, String gender, String address, String country, String phone_number, String nik, String date_of_birth){
        this.username = username;
        this.full_name = full_name;
        this.email = email;
        this.gender = gender;
        this.address = address;
        this.country = country;
        this.phone_number = phone_number;
        this.nik = nik;
        this.date_of_birth = date_of_birth;
    }

    public static void writeNewUser(String username, String full_name, String email, String gender, String address, String country, String phone_number, String nik, String date_of_birth) {

        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
        User user = new User(username, full_name, email, gender, address, country,phone_number, nik, date_of_birth);

        mDatabase.child("Users").child(current_user.getUid()).child("parents").setValue(user);
    }


}