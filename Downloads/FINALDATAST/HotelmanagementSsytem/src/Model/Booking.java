/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author aayusharijal
 */
import java.util.Date;



public class Booking {
    private int id = -1;
    private final String guestName, phone;
    private final int roomNumber;
    private final Date checkIn, checkOut;
    private final double totalPrice;
    private String status;

    public Booking(String guestName, String phone, int roomNumber, Date checkIn, Date checkOut, double totalPrice, String status) throws Exception {
        if (guestName == null || guestName.trim().isEmpty()) throw new Exception("Guest name is required");
        String trimmedPhone = (phone == null) ? "" : phone.trim();
int length = trimmedPhone.length();

// This helps you see exactly what is wrong in the popup
if (trimmedPhone.isEmpty()) {
    throw new Exception("Error: Phone field is empty!");
}

if (length != 10) {
    throw new Exception("Phone has " + length + " digits. It needs exactly 10!");
}

for (char c : trimmedPhone.toCharArray()) {
    if (!Character.isDigit(c)) {
        throw new Exception("Character '" + c + "' is not a number!");
    }
}
        if (roomNumber <= 0) throw new Exception("Invalid room number");
        if (checkIn == null || checkOut == null) throw new Exception("Dates are required");
        if (!checkIn.before(checkOut)) throw new Exception("Check-out must be after check-in");
        if (totalPrice < 0) throw new Exception("Total price cannot be negative");

        this.guestName = guestName.trim();
        this.phone = phone ;
        this.roomNumber = roomNumber;
        this.checkIn = new Date(checkIn.getTime()); // Defensive copy
        this.checkOut = new Date(checkOut.getTime());
        this.totalPrice = totalPrice;
        this.status = (status == null) ? "Pending" : status.trim();
    }

    public int getId() { return id; }
    public void setId(int id) { 
        if (this.id != -1) throw new IllegalStateException("ID already set");
        this.id = id; 
    }
    public String getGuestName() { return guestName; }
    public String getPhone() { return phone; }
    public int getRoomNumber() { return roomNumber; }
    public Date getCheckIn() { return new Date(checkIn.getTime()); }
    public Date getCheckOut() { return new Date(checkOut.getTime()); }
    public double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }
}