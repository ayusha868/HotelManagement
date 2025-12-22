/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Room {
    private int roomNo;
    private String roomType;
    private int beds;
    private double pricePerNight;
    private String status; // "Available", "Booked"

    public Room(int roomNo, String roomType, int beds, double pricePerNight, String status) {
        this.roomNo = roomNo;
        this.roomType = roomType;
        this.beds = beds;
        this.pricePerNight = pricePerNight;
        this.status = status;
    }

    // Getters & Setters
    public int getRoomNo() { return roomNo; }
    public void setRoomNo(int roomNo) { this.roomNo = roomNo; }
    public String getRoomType() { return roomType; }
    public int getBeds() { return beds; }
    public double getPricePerNight() { return pricePerNight; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Room{" + roomNo + ", " + roomType + ", $" + pricePerNight + "}";
    }
}