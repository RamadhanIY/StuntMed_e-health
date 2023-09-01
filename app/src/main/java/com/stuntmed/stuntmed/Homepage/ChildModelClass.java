package com.stuntmed.stuntmed.Homepage;

public class ChildModelClass {

    public String nama_anak, kategori;
    int pic_baby, pic_category, pic_gender;

    public String berat, tinggi, lk;


    public ChildModelClass(int pic_baby, String nama_anak, int pic_gender, String kategori, int pic_category, String berat, String tinggi, String lk) {
        this.pic_baby = pic_baby;
        this.nama_anak = nama_anak;
        this.pic_gender = pic_gender;
        this.kategori = kategori;
        this.pic_category = pic_category;
        this.berat = berat;
        this.tinggi = tinggi;
        this.lk = lk;
    }
}
