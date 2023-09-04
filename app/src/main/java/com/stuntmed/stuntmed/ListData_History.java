package com.stuntmed.stuntmed;

import android.media.Image;
import android.widget.ImageView;

public class ListData_History {
    int Photo;
    String NamaHistory;
    String hasil_desc;
    String desc;

    String tanggal;
    String nik;

    public ListData_History(String nik, String namaHistory, String tanggal) {
        this.NamaHistory = namaHistory;
        this.tanggal = tanggal;
        this.nik = nik;
    }
}
