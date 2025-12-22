/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import View.UserDashboard;
import Model.HotelData;
import Model.Room;
import Model.Booking;
import Model.Validator;
import Model.DataHelper;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CustomerController {
    private UserDashboard userView;
    private HotelData hotelData;
    private String currentCustomer;
    private DefaultTableModel availableRoomsModel;
    private DefaultTableModel bookingHistoryModel;
    
    public CustomerController(UserDashboard userView, String customerName) {
        this.userView = userView;
        this.hotelData = new HotelData();
        this.currentCustomer = customerName;
    }
    
    // Initialize tables
    public void initializeAvailableRoomsTable(JTable availableRoomsTable) {
        String[] columns = {"Room Number", "Room Type", "Beds", "Price/Night", "Status", "Amenities"};
        availableRoomsModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        availableRoomsTable.setModel(availableRoomsModel);
        loadAvailableRooms();
    }
    
    public void initializeBookingHistoryTable(JTable bookingHistoryTable) {
        String[] columns = {"Booking ID", "Room Type", "Check In", "Check Out", "Price", "Status"};
        bookingHistoryModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        bookingHistoryTable.setModel(bookingHistoryModel);
        loadBookingHistory();
    }
    
    // Load data methods
    public void loadAvailableRooms() {
        availableRoomsModel.setRowCount(0);
        ArrayList<Room> availableRooms = hotelData.searchAvailableRooms();
        for (Room room : availableRooms) {
            availableRoomsModel.addRow(new Object[]{
                room.getRoomNumber(),
                room.getRoomType(),
                room.getBeds(),
                String.format("$%.2f", room.getPricePerNight()),
                room.getStatus(),
                room.getAmenities()
            });
        }
    }
    
    public void loadBookingHistory() {
        bookingHistoryModel.setRowCount(0);
        ArrayList<Booking> customerBookings = hotelData.getCustomerBookings(currentCustomer);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        for (Booking booking : customerBookings) {
            // Get room type from room number
            Room room = hotelData.getRoom(booking.getRoomNumber());
            String roomType = (room != null) ? room.getRoomType() : "Unknown";
            
            bookingHistoryModel.addRow(new Object[]{
                booking.getBookingId(),
                roomType,
                sdf.format(booking.getCheckInDate()),
                sdf.format(booking.getCheckOutDate()),
                String.format("$%.2f", booking.getTotalPrice()),
                booking.getStatus()
            });
        }
    }
    
    // Booking operations
    public String bookRoom(String roomNumber, String checkInStr, String checkOutStr, int nights) {
        try {
            // Parse dates
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date checkIn = sdf.parse(checkInStr);
            Date checkOut = sdf.parse(checkOutStr);
            
            // Validate dates
            String dateError = Validator.validateDates(checkIn, checkOut);
            if (dateError != null) {
                JOptionPane.showMessageDialog(userView, dateError, "Validation Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            
            // Check if room is available
            Room room = hotelData.getRoom(roomNumber);
            if (room == null) {
                JOptionPane.showMessageDialog(userView, "Room not found", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            
            if (!"Available".equals(room.getStatus())) {
                JOptionPane.showMessageDialog(userView, "Room is not available", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            
            // Calculate price
            double totalPrice = DataHelper.calculatePrice(room.getPricePerNight(), nights);
            
            // Create booking
            String bookingId = hotelData.createBooking(roomNumber, currentCustomer, checkIn, checkOut, totalPrice);
            
            if (bookingId != null) {
                loadAvailableRooms();
                loadBookingHistory();
                JOptionPane.showMessageDialog(userView, 
                    "Booking successful! Your Booking ID: " + bookingId, 
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            }
            
            return bookingId;
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(userView, 
                "Invalid date format. Please use dd/MM/yyyy", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }
    
    public boolean cancelBooking(String bookingId) {
        int confirm = JOptionPane.showConfirmDialog(userView, 
            "Are you sure you want to cancel booking " + bookingId + "?", 
            "Confirm Cancellation", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean cancelled = hotelData.cancelBooking(bookingId);
            
            if (cancelled) {
                loadBookingHistory();
                loadAvailableRooms();
                JOptionPane.showMessageDialog(userView, "Booking cancelled successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(userView, "Failed to cancel booking", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            return cancelled;
        }
        return false;
    }
    
    // Sorting methods
    public void sortAvailableRoomsByNumber() {
        ArrayList<Room> allRooms = hotelData.searchAvailableRooms();
        ArrayList<Room> sortedRooms = sortRoomsByNumber(allRooms);
        updateAvailableRoomsTable(sortedRooms);
        JOptionPane.showMessageDialog(userView, "Available rooms sorted by room number", "Sorted", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void sortAvailableRoomsByPrice() {
        ArrayList<Room> allRooms = hotelData.searchAvailableRooms();
        ArrayList<Room> sortedRooms = sortRoomsByPrice(allRooms);
        updateAvailableRoomsTable(sortedRooms);
        JOptionPane.showMessageDialog(userView, "Available rooms sorted by price", "Sorted", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private ArrayList<Room> sortRoomsByNumber(ArrayList<Room> rooms) {
        // Bubble sort implementation
        ArrayList<Room> sorted = new ArrayList<>(rooms);
        for (int i = 0; i < sorted.size() - 1; i++) {
            for (int j = 0; j < sorted.size() - i - 1; j++) {
                String num1 = sorted.get(j).getRoomNumber();
                String num2 = sorted.get(j + 1).getRoomNumber();
                
                if (num1.compareTo(num2) > 0) {
                    Room temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                }
            }
        }
        return sorted;
    }
    
    private ArrayList<Room> sortRoomsByPrice(ArrayList<Room> rooms) {
        // Bubble sort implementation
        ArrayList<Room> sorted = new ArrayList<>(rooms);
        for (int i = 0; i < sorted.size() - 1; i++) {
            for (int j = 0; j < sorted.size() - i - 1; j++) {
                double price1 = sorted.get(j).getPricePerNight();
                double price2 = sorted.get(j + 1).getPricePerNight();
                
                if (price1 > price2) {
                    Room temp = sorted.get(j);
                    sorted.set(j, sorted.get(j + 1));
                    sorted.set(j + 1, temp);
                }
            }
        }
        return sorted;
    }
    
    private void updateAvailableRoomsTable(ArrayList<Room> rooms) {
        availableRoomsModel.setRowCount(0);
        for (Room room : rooms) {
            availableRoomsModel.addRow(new Object[]{
                room.getRoomNumber(),
                room.getRoomType(),
                room.getBeds(),
                String.format("$%.2f", room.getPricePerNight()),
                room.getStatus(),
                room.getAmenities()
            });
        }
    }
    
    // Search methods
    public void searchAvailableRooms(String searchTerm) {
        ArrayList<Room> allRooms = hotelData.searchAvailableRooms();
        ArrayList<Room> results = new ArrayList<>();
        
        searchTerm = searchTerm.toLowerCase();
        for (Room room : allRooms) {
            if (room.getRoomNumber().toLowerCase().contains(searchTerm) ||
                room.getRoomType().toLowerCase().contains(searchTerm) ||
                room.getAmenities().toLowerCase().contains(searchTerm)) {
                results.add(room);
            }
        }
        
        updateAvailableRoomsTable(results);
        
        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(userView, "No rooms found matching: " + searchTerm, "Search Results", JOptionPane.WARNING_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(userView, "Found " + results.size() + " room(s)", "Search Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    // Get room information
    public Room getRoomInfo(String roomNumber) {
        return hotelData.getRoom(roomNumber);
    }
    
    // Get booking information
    public Booking getBookingInfo(String bookingId) {
        ArrayList<Booking> customerBookings = hotelData.getCustomerBookings(currentCustomer);
        for (Booking booking : customerBookings) {
            if (booking.getBookingId().equals(bookingId)) {
                return booking;
            }
        }
        return null;
    }
    
    // Refresh data
    public void refreshData() {
        loadAvailableRooms();
        loadBookingHistory();
        JOptionPane.showMessageDialog(userView, "Data refreshed", "Refresh", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Get current customer name
    public String getCurrentCustomer() {
        return currentCustomer;
    }
}