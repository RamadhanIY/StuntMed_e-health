package com.stuntmed.stuntmed.Databases;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.stuntmed.stuntmed.Method;

public class BeratTinggiLKBulanan {

    public String dateformat;
    public String nik;
    public String bulan;
    public String berat;
    public  String tinggi;
    public String label_berat,label_tinggi,label_lk, label_stunting;
    public String lk;

    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance(Method.database_url).getReference();

    public BeratTinggiLKBulanan(){

    }

    public BeratTinggiLKBulanan(String berat, String tinggi, String lk,String label_berat,String label_tinggi,String label_lk, String label_stunting){
        this.berat = berat;
        this.tinggi = tinggi;
        this.lk = lk;
        this.label_berat = label_berat;
        this.label_tinggi = label_tinggi;
        this.label_lk = label_lk;
        this.label_stunting = label_stunting;
    }

    public static  void writenewBeratTinggiLKBulanan(String bulan,String nik, String berat, String tinggi, String lk,String label_berat,String label_tinggi,String label_lk, String label_stunting){

        FirebaseUser current_user = Method.getCurrentUser();
        BeratTinggiLKBulanan beratTinggiLKBulanan = new BeratTinggiLKBulanan(berat,tinggi,lk,label_berat,label_tinggi,label_lk, label_stunting);
        mDatabase.child("databulanan").child(nik).child(bulan).setValue(beratTinggiLKBulanan);
    }
    public String getBerat() {
        return berat;
    }

    // Setter untuk berat
    public void setBerat(String berat) {
        this.berat = berat;
    }

    // Getter untuk tinggi
    public String getTinggi() {
        return tinggi;
    }

    // Setter untuk tinggi
    public void setTinggi(String tinggi) {
        this.tinggi = tinggi;
    }

    // Getter untuk lk
    public String getLk() {
        return lk;
    }

    // Setter untuk lk
    public void setLk(String lk) {
        this.lk = lk;
    }

}
