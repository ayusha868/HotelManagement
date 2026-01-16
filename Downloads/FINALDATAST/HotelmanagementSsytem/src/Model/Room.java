/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author aayusharijal
 */
public class Room implements Comparable<Room> {
    private final int roomNumber;
    private String roomType;
    private int beds;
    private double pricePerNight;
    private String status;
    private int squareFeet;
    private int maxGuests;

    public Room(int roomNumber, String roomType, int beds, double pricePerNight,
                String status, int squareFeet, int maxGuests) throws ValidationException {
        if (roomNumber <= 0) throw new ValidationException("Room number must be positive");
        if (roomType == null || roomType.trim().isEmpty()) throw new ValidationException("Room type is required");
        if (beds < 1) throw new ValidationException("Number of beds must be at least 1");
        if (pricePerNight <= 0) throw new ValidationException("Price per night must be positive");
        if (squareFeet <= 0) throw new ValidationException("Square feet must be positive");
        if (maxGuests < 1) throw new ValidationException("Max guests must be at least 1");

        String normalizedStatus = (status == null) ? "Available" : status.trim();
        if (!normalizedStatus.equals("Available") && !normalizedStatus.equals("Booked") && !normalizedStatus.equals("Maintenance")) {
            throw new ValidationException("Invalid room status");
        }

        this.roomNumber = roomNumber;
        this.roomType = roomType.trim();
        this.beds = beds;
        this.pricePerNight = pricePerNight;
        this.status = normalizedStatus;
        this.squareFeet = squareFeet;
        this.maxGuests = maxGuests;
    }

    public int getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public int getBeds() { return beds; }
    public double getPricePerNight() { return pricePerNight; }
    public String getStatus() { return status; }
    public int getSquareFeet() { return squareFeet; }
    public int getMaxGuests() { return maxGuests; }

    public void setStatus(String status) throws ValidationException {
        String s = (status == null) ? "Available" : status.trim();
        if (!s.equals("Available") && !s.equals("Booked") && !s.equals("Maintenance")) throw new ValidationException("Invalid status");
        this.status = s;
    }

    @Override
    public int compareTo(Room other) { return Integer.compare(this.roomNumber, other.roomNumber); }
}