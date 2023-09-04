package com.stuntmed.stuntmed;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {
    // Validasi alamat email
    public static boolean isValidEmail(String email) {
        // String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z]+.[a-zA-Z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    // Validasi tanggal dengan format "Sep 2, 2020"
    public static boolean isValidDate(String date) {
        String regex = "^[A-Z][a-z]{2} [1-3]?[0-9]?, \\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    // Validasi Nomor Induk Kependudukan (NIK) dengan format "ddmmyy-xxxxx"
    public static boolean isValidNIK(String nik) {
        String regex = "^\\d{16}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nik);
        return matcher.matches();
    }

    // Validasi nomor telepon dengan format "08xx-xxxx-xxxx"
    public static boolean isValidPhoneNumber(String phoneNumber) {
        String regex = "^\\+?\\d{12,14}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}
