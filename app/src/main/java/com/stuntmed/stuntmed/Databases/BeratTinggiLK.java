package com.stuntmed.stuntmed.Databases;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.stuntmed.stuntmed.Method;

public class BeratTinggiLK {

    public String nik;
    public String berat;
    public  String tinggi;
    public String lk;

    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance(Method.database_url).getReference();

    public BeratTinggiLK(){

    }

    public BeratTinggiLK(String berat, String tinggi, String lk){
        this.berat = berat;
        this.tinggi = tinggi;
        this.lk = lk;
    }

    public static  void writenewBeratTinggiLK(String nik, String berat, String tinggi, String lk){

        Method.setValueOnDatabase("Users/"+Method.getCurrentUser().getUid()+"/babies/"+ nik + "/berat",berat);
        Method.setValueOnDatabase("Users/"+Method.getCurrentUser().getUid()+"/babies/"+ nik + "/tinggi",tinggi);
        Method.setValueOnDatabase("Users/"+Method.getCurrentUser().getUid()+"/babies/"+ nik + "/lk",lk);

    }

}
