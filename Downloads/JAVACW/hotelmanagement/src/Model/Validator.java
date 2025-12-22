/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Validator {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    // Existing methods...
    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isValidDateFormat(String date) {
        if (date == null || !date.matches("\\d{4}-\\d{2}-\\d{2}")) {
            return false;
        }
        try {
            LocalDate.parse(date, DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isFutureDate(String date) {
        try {
            LocalDate inputDate = LocalDate.parse(date, DATE_FORMATTER);
            return !inputDate.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean isCheckInBeforeCheckOut(String checkIn, String checkOut) {
        try {
            LocalDate in = LocalDate.parse(checkIn, DATE_FORMATTER);
            LocalDate out = LocalDate.parse(checkOut, DATE_FORMATTER);
            return in.isBefore(out) || in.isEqual(out); // allow same-day (if hotel allows)
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    // Optional: Minimum stay (e.g. at least 1 night)
    public static boolean hasMinimumStay(String checkIn, String checkOut) {
        try {
            LocalDate in = LocalDate.parse(checkIn, DATE_FORMATTER);
            LocalDate out = LocalDate.parse(checkOut, DATE_FORMATTER);
            long nights = java.time.temporal.ChronoUnit.DAYS.between(in, out);
            return nights >= 1;
        } catch (Exception e) {
            return false;
        }
    }

    // Simple name validation (basic version)
    public static boolean isValidName(String name) {
        if (isEmpty(name)) return false;
        // Allow letters, spaces, hyphens, apostrophes (common in names)
        return name.matches("^[a-zA-Z\\s'-]+$") && name.length() >= 2 && name.length() <= 60;
    }

    // Very basic email check (optional - only if you add email field later)
    public static boolean isValidEmail(String email) {
        if (isEmpty(email)) return false;
        return email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    }
}