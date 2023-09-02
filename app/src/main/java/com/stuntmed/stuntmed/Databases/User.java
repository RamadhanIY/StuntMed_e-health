package com.stuntmed.stuntmed.Databases;

import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.stuntmed.stuntmed.MainActivity;
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
    public String uri;

    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance(Method.database_url).getReference();
    public static User user;


    public User(){
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String uri,String username, String full_name, String email, String gender, String address, String country, String phone_number, String nik, String date_of_birth){
        this.uri = uri;
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




    private void getData(){
        DatabaseReference mDatabase = FirebaseDatabase
                .getInstance(Method.database_url)
                .getReference("Users/" + FirebaseAuth.getInstance().getCurrentUser().getUid() + "/parents");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                // tambahkan code di sini untuk mengambil data
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // tambahkan code ketika data gagal diambil
            }
        });
    }

    public static void writeNewUser(String username, String full_name, String email) {
        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
        User user = new User(null,username, full_name, email, null, null, null,null, null, null);

        mDatabase = FirebaseDatabase.getInstance(Method.database_url).getReference();
        mDatabase.child("Users").child(current_user.getUid()).child("parents").setValue(user);
    }
    public static void writeNewParents(String uri,String username, String full_name, String email, String gender, String address, String country, String phone_number, String nik, String date_of_birth) {
        FirebaseUser current_user = FirebaseAuth.getInstance().getCurrentUser();
        User user = new User(uri,username, full_name, email, gender, address, country,phone_number, nik, date_of_birth);

        mDatabase = FirebaseDatabase.getInstance(Method.database_url).getReference();
        mDatabase.child("Users").child(current_user.getUid()).child("parents").setValue(user);
    }


}