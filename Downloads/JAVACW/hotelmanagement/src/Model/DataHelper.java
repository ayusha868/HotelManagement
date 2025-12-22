/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataHelper {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public static String formatDate(Date date) {
        if (date == null) return "";
        return dateFormat.format(date);
    }
    
    public static Date parseDate(String dateString) throws Exception {
        return dateFormat.parse(dateString);
    }
    
    public static boolean isValidDate(String dateString) {
        try {
            dateFormat.parse(dateString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public static double calculatePrice(double pricePerNight, int nights) {
        return pricePerNight * nights;
    }
    
    public static int calculateNights(Date checkIn, Date checkOut) {
        if (checkIn == null || checkOut == null) return 0;
        long diff = checkOut.getTime() - checkIn.getTime();
        return (int) (diff / (1000 * 60 * 60 * 24));
    }
    
    public static boolean isFutureDate(Date date) {
        Date today = new Date();
        return date.after(today);
    }
}