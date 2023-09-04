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
    public String label1,label2,label3;
    public String lk;

    private static DatabaseReference mDatabase = FirebaseDatabase.getInstance(Method.database_url).getReference();

    public BeratTinggiLKBulanan(){

    }

    public BeratTinggiLKBulanan(String berat, String tinggi, String lk,String label1,String label2,String label3){
        this.berat = berat;
        this.tinggi = tinggi;
        this.lk = lk;
        this.label1 = label1;
        this.label2 = label2;
        this.label3 = label3;
    }

    public static  void writenewBeratTinggiLKBulanan(String bulan,String nik, String berat, String tinggi, String lk,String label1,String label2,String label3){

        FirebaseUser current_user = Method.getCurrentUser();
        BeratTinggiLKBulanan beratTinggiLKBulanan = new BeratTinggiLKBulanan(berat,tinggi,lk,label1,label2,label3);
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
