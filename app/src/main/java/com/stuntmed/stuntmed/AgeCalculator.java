package com.stuntmed.stuntmed;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;
import org.threeten.bp.temporal.ChronoUnit;

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
}
