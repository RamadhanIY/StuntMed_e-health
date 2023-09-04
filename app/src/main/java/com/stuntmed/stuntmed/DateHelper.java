package com.stuntmed.stuntmed;

import java.util.Calendar;

public class DateHelper {

    public static String getTodayDate() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH) + 1;  // Bulan dimulai dari 0, jadi kita tambah 1
        int year = calendar.get(Calendar.YEAR);

        return String.format("%02d-%02d-%d", day, month, year);
    }

}
