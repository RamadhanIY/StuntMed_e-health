package com.stuntmed.stuntmed;

import android.media.Image;
import android.widget.ImageView;

public class ListData_History {
    int Photo;
    String NamaHistory;
    String hasil_desc;
    String desc;

    String tanggal;

    public ListData_History(int photo, String namaHistory, String tanggal, String hasil_desc, String desc) {
        this.Photo = photo;
        this.NamaHistory = namaHistory;
        this.hasil_desc = hasil_desc;
        this.desc = desc;
        this.tanggal = tanggal;
    }
}
