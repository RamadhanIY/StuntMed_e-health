package com.stuntmed.stuntmed;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoUnit;

import java.util.Locale;

public class AgeCalculator {

    public static String calculateAgeInMonthsAndDays(String birthDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy");
        LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
        LocalDate currentDate = LocalDate.now();

        long totalMonthsBetween = ChronoUnit.MONTHS.between(birthDate, currentDate);
        LocalDate dateAfterMonths = birthDate.plusMonths(totalMonthsBetween);

        long daysBetween = ChronoUnit.DAYS.between(dateAfterMonths, currentDate);

        return totalMonthsBetween + " months, " + daysBetween + " days";
    }


    public static int calculateAgeInMonths(String birthDateStr) {
        // Membuat formatter dengan format yang sesuai
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);

        // Mengonversi string ke LocalDate
        LocalDate birthDate = LocalDate.parse(birthDateStr, formatter);
        LocalDate currentDate = LocalDate.now();

        // Menghitung selisih dalam bulan
        return (int) ChronoUnit.MONTHS.between(birthDate, currentDate);
    }

}
