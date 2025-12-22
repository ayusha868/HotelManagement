/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.ArrayList;
import java.util.List;

public class HotelData {
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Booking> bookings = new ArrayList<>();
    private static int nextBookingId = 1001;

    // Sample data
    static {
        rooms.add(new Room(101, "Single", 1, 80.0, "Available"));
        rooms.add(new Room(102, "Double", 2, 120.0, "Available"));
        rooms.add(new Room(201, "Deluxe", 2, 180.0, "Available"));
        rooms.add(new Room(301, "Suite", 4, 300.0, "Available"));
    }

    public static List<Room> getAllRooms() {
        return new ArrayList<>(rooms);
    }

    public static List<Room> getAvailableRooms() {
        ArrayList<Room> available = new ArrayList<>();
        for (Room r : rooms) {
            if ("Available".equalsIgnoreCase(r.getStatus())) {
                available.add(r);
            }
        }
        return available;
    }

    public static int getTotalRooms() {
        return rooms.size();
    }

    public static int getAvailableRoomsCount() {
        int count = 0;
        for (Room r : rooms) {
            if ("Available".equalsIgnoreCase(r.getStatus())) {
                count++;
            }
        }
        return count;
    }

    public static Room findRoomByNumber(int roomNo) {
        for (Room r : rooms) {
            if (r.getRoomNo() == roomNo) {
                return r;
            }
        }
        return null;
    }

    public static void addBooking(Booking booking) {
        Booking newBooking = new Booking(
            nextBookingId++,
            booking.getRoomNo(),
            booking.getCustomerName(),
            booking.getCheckInDate(),
            booking.getCheckOutDate(),
            booking.getTotalPrice(),
            "Confirmed"
        );
        bookings.add(newBooking);

        Room room = findRoomByNumber(newBooking.getRoomNo());
        if (room != null) {
            room.setStatus("Booked");
        }
    }

    public static List<Booking> getBookingsByCustomer(String customerName) {
        ArrayList<Booking> customerBookings = new ArrayList<>();
        for (Booking b : bookings) {
            if (b.getCustomerName().equalsIgnoreCase(customerName)) {
                customerBookings.add(b);
            }
        }
        return customerBookings;
    }

    public static Booking findBookingById(int id) {
        for (Booking b : bookings) {
            if (b.getBookingId() == id) {
                return b;
            }
        }
        return null;
    }

    public static boolean deleteBooking(int bookingId, String requestingCustomer) {
        Booking booking = findBookingById(bookingId);
        if (booking == null || !booking.getCustomerName().equalsIgnoreCase(requestingCustomer)) {
            return false;
        }

        bookings.remove(booking);
        Room room = findRoomByNumber(booking.getRoomNo());
        if (room != null) {
            room.setStatus("Available");
        }
        return true;
    }

    public static boolean updateBookingDates(int bookingId, String newIn, String newOut, double newPrice, String requestingCustomer) {
        Booking booking = findBookingById(bookingId);
        if (booking == null || !booking.getCustomerName().equalsIgnoreCase(requestingCustomer)) {
            return false;
        }

        booking.setCheckInDate(newIn);
        booking.setCheckOutDate(newOut);
        booking.setTotalPrice(newPrice);
        return true;
    }
}