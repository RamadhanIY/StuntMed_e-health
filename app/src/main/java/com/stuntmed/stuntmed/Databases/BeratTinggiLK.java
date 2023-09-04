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
    public String label_berat,label_tinggi,label_lk, label_stunting;

    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance(Method.database_url).getReference();

    public BeratTinggiLK(){

    }

    public BeratTinggiLK(String berat, String tinggi, String lk, String label_berat,String label_tinggi,String label_lk, String label_stunting){
        this.berat = berat;
        this.tinggi = tinggi;
        this.lk = lk;
        this.label_berat = label_berat;
        this.label_tinggi = label_tinggi;
        this.label_lk = label_lk;
        this.label_stunting = label_stunting;
    }

    public static  void writenewBeratTinggiLK(String nik, String berat, String tinggi, String lk, String label_berat,String label_tinggi,String label_lk, String label_stunting){

        Method.setValueOnDatabase("Users/"+Method.getCurrentUser().getUid()+"/babies/"+ nik + "/berat",berat);
        Method.setValueOnDatabase("Users/"+Method.getCurrentUser().getUid()+"/babies/"+ nik + "/tinggi",tinggi);
        Method.setValueOnDatabase("Users/"+Method.getCurrentUser().getUid()+"/babies/"+ nik + "/lk",lk);

        Method.setValueOnDatabase("Users/"+Method.getCurrentUser().getUid()+"/babies/"+ nik + "/label_berat",label_berat);
        Method.setValueOnDatabase("Users/"+Method.getCurrentUser().getUid()+"/babies/"+ nik + "/label_tinggi",label_tinggi);
        Method.setValueOnDatabase("Users/"+Method.getCurrentUser().getUid()+"/babies/"+ nik + "/label_lk",label_lk);
        Method.setValueOnDatabase("Users/"+Method.getCurrentUser().getUid()+"/babies/"+ nik + "/label_stunting",label_stunting);

    }

}
