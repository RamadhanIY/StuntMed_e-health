package com.stuntmed.stuntmed;

public class DataAddChild  {
    String NameChild, umur,nik;
//    int photo;

    public DataAddChild(String nameChild, String umur, String nik) {
        this.NameChild = nameChild;
        this.umur = umur;
        this.nik =nik;
//        this.photo = photo;
    }
    public String getNik() {
        return nik;
    }

    // Jika Anda belum memiliki setter, tambahkan juga:
    public void setNik(String nik) {
        this.nik = nik;
    }
}
