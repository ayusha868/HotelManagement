/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Booking {
    private int bookingId;
    private int roomNo;
    private String customerName;
    private String checkInDate;
    private String checkOutDate;
    private double totalPrice;
    private String status; // "Confirmed", "Cancelled"

    public Booking(int bookingId, int roomNo, String customerName,
                   String checkInDate, String checkOutDate, double totalPrice, String status) {
        this.bookingId = bookingId;
        this.roomNo = roomNo;
        this.customerName = customerName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    // Getters
    public int getBookingId() { return bookingId; }
    public int getRoomNo() { return roomNo; }
    public String getCustomerName() { return customerName; }
    public String getCheckInDate() { return checkInDate; }
    public String getCheckOutDate() { return checkOutDate; }
    public double getTotalPrice() { return totalPrice; }
    public String getStatus() { return status; }

    public void setCheckInDate(String checkInDate) { this.checkInDate = checkInDate; }
    public void setCheckOutDate(String checkOutDate) { this.checkOutDate = checkOutDate; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }
    public void setStatus(String status) { this.status = status; }
}