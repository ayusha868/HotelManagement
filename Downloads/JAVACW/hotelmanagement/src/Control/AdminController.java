/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import View.AdminDashboard;
import Model.HotelData;
import Model.Room;
import Model.Booking;
import Model.Validator;
import Model.DataHelper;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Date;

public class AdminController {
    private AdminDashboard adminView;
    private HotelData hotelData;
    private DefaultTableModel roomsTableModel;
    private DefaultTableModel bookingsTableModel;
    
    public AdminController(AdminDashboard adminView) {
        this.adminView = adminView;
        this.hotelData = new HotelData();
    }
    
    // Initialize data in tables
    public void initializeRoomsTable(JTable roomsTable) {
        String[] columns = {"Room Number", "Room Type", "Beds", "Price/Night", "Status", "Amenities"};
        roomsTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        roomsTable.setModel(roomsTableModel);
        loadAllRooms();
    }
    
    public void initializeBookingsTable(JTable bookingsTable) {
        String[] columns = {"Booking ID", "Room Number", "Customer Name", "Check-in", "Check-out", "Status", "Total Price"};
        bookingsTableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        bookingsTable.setModel(bookingsTableModel);
        loadAllBookings();
    }
    
    // Load data methods - FIXED HERE
    public void loadAllRooms() {
        roomsTableModel.setRowCount(0);
        ArrayList<Room> rooms = hotelData.getAllRooms();
        for (Room room : rooms) {
            roomsTableModel.addRow(new Object[]{
                room.getRoomNumber(),
                room.getRoomType(),
                room.getBeds(),
                String.format("$%.2f", room.getPricePerNight()),
                room.getStatus(),
                room.getAmenities()
            });
        }
    }
    
    public void loadAllBookings() {
        bookingsTableModel.setRowCount(0);
        LinkedList<Booking> bookings = hotelData.getBookings(); // This returns LinkedList
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        // Iterate through LinkedList
        for (Booking booking : bookings) {
            bookingsTableModel.addRow(new Object[]{
                booking.getBookingId(),
                booking.getRoomNumber(),
                booking.getCustomerName(),
                sdf.format(booking.getCheckInDate()),
                sdf.format(booking.getCheckOutDate()),
                booking.getStatus(),
                String.format("$%.2f", booking.getTotalPrice())
            });
        }
    }
    
    // If you need to convert LinkedList to ArrayList, use this method:
    public ArrayList<Booking> getBookingsAsArrayList() {
        LinkedList<Booking> linkedList = hotelData.getBookings();
        ArrayList<Booking> arrayList = new ArrayList<>();
        
        // Convert LinkedList to ArrayList
        for (Booking booking : linkedList) {
            arrayList.add(booking);
        }
        return arrayList;
    }
    
    // CRUD Operations for Rooms
    public boolean addRoom(String roomNumber, String roomType, int beds, 
                          double price, String status, String amenities) {
        // Validate inputs
        String roomNumberError = Validator.validateRoomNumber(roomNumber);
        if (roomNumberError != null) {
            JOptionPane.showMessageDialog(adminView, roomNumberError, "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        String priceError = Validator.validatePrice(price);
        if (priceError != null) {
            JOptionPane.showMessageDialog(adminView, priceError, "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        String bedsError = Validator.validateBeds(beds);
        if (bedsError != null) {
            JOptionPane.showMessageDialog(adminView, bedsError, "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        String roomTypeError = Validator.validateRoomType(roomType);
        if (roomTypeError != null) {
            JOptionPane.showMessageDialog(adminView, roomTypeError, "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Check if room already exists
        if (hotelData.getRoom(roomNumber) != null) {
            JOptionPane.showMessageDialog(adminView, "Room number already exists", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        Room newRoom = new Room(roomNumber, roomType, beds, price, status, amenities);
        boolean added = hotelData.addRoom(newRoom);
        
        if (added) {
            loadAllRooms();
            JOptionPane.showMessageDialog(adminView, "Room added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(adminView, "Failed to add room", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return added;
    }
    
    public boolean updateRoom(String roomNumber, String roomType, int beds, 
                             double price, String status, String amenities) {
        Room room = hotelData.getRoom(roomNumber);
        if (room == null) {
            JOptionPane.showMessageDialog(adminView, "Room not found", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // Validate inputs
        String priceError = Validator.validatePrice(price);
        if (priceError != null) {
            JOptionPane.showMessageDialog(adminView, priceError, "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        String bedsError = Validator.validateBeds(beds);
        if (bedsError != null) {
            JOptionPane.showMessageDialog(adminView, bedsError, "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        String roomTypeError = Validator.validateRoomType(roomType);
        if (roomTypeError != null) {
            JOptionPane.showMessageDialog(adminView, roomTypeError, "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        Room updatedRoom = new Room(roomNumber, roomType, beds, price, status, amenities);
        boolean updated = hotelData.updateRoom(roomNumber, updatedRoom);
        
        if (updated) {
            loadAllRooms();
            JOptionPane.showMessageDialog(adminView, "Room updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(adminView, "Failed to update room", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        return updated;
    }
    
    public boolean deleteRoom(String roomNumber) {
        int confirm = JOptionPane.showConfirmDialog(adminView, 
            "Are you sure you want to delete room " + roomNumber + "?", 
            "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean deleted = hotelData.deleteRoom(roomNumber);
            
            if (deleted) {
                loadAllRooms();
                JOptionPane.showMessageDialog(adminView, "Room deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(adminView, "Failed to delete room", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            return deleted;
        }
        return false;
    }
    
    // Sorting methods
    public void sortRoomsByNumber() {
        ArrayList<Room> sortedRooms = hotelData.sortRoomsByNumber();
        updateRoomsTable(sortedRooms);
        JOptionPane.showMessageDialog(adminView, "Rooms sorted by room number", "Sorted", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void sortRoomsByPrice() {
        ArrayList<Room> sortedRooms = hotelData.sortRoomsByPrice();
        updateRoomsTable(sortedRooms);
        JOptionPane.showMessageDialog(adminView, "Rooms sorted by price", "Sorted", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void updateRoomsTable(ArrayList<Room> rooms) {
        roomsTableModel.setRowCount(0);
        for (Room room : rooms) {
            roomsTableModel.addRow(new Object[]{
                room.getRoomNumber(),
                room.getRoomType(),
                room.getBeds(),
                String.format("$%.2f", room.getPricePerNight()),
                room.getStatus(),
                room.getAmenities()
            });
        }
    }
    
    // Booking operations
    public boolean cancelBooking(String bookingId) {
        int confirm = JOptionPane.showConfirmDialog(adminView, 
            "Are you sure you want to cancel booking " + bookingId + "?", 
            "Confirm Cancellation", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            boolean cancelled = hotelData.cancelBooking(bookingId);
            
            if (cancelled) {
                loadAllBookings();
                loadAllRooms(); // Update room status
                JOptionPane.showMessageDialog(adminView, "Booking cancelled successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(adminView, "Failed to cancel booking", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
            return cancelled;
        }
        return false;
    }
    
    // Search methods
    public void searchRoom(String roomNumber) {
        Room room = hotelData.binarySearchRoom(roomNumber);
        
        if (room != null) {
            roomsTableModel.setRowCount(0); // Clear table
            roomsTableModel.addRow(new Object[]{
                room.getRoomNumber(),
                room.getRoomType(),
                room.getBeds(),
                String.format("$%.2f", room.getPricePerNight()),
                room.getStatus(),
                room.getAmenities()
            });
            JOptionPane.showMessageDialog(adminView, "Room found: " + room, "Search Result", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(adminView, "Room not found: " + roomNumber, "Search Result", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    // Statistics methods
    public int getTotalRooms() {
        return hotelData.getTotalRooms();
    }
    
    public int getAvailableRooms() {
        return hotelData.getAvailableRooms();
    }
    
    public int getBookedRooms() {
        return hotelData.getBookedRooms();
    }
    
    public int getMaintenanceRooms() {
        return hotelData.getMaintenanceRooms();
    }
    
    public double getTotalRevenue() {
        return hotelData.getTotalRevenue();
    }
    
    // Get room data for editing
    public Room getRoomData(String roomNumber) {
        return hotelData.getRoom(roomNumber);
    }
    
    // Get booking data
    public Booking getBookingData(String bookingId) {
        LinkedList<Booking> bookings = hotelData.getBookings(); // Use LinkedList
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                return booking;
            }
        }
        return null;
    }
    
    // Refresh all data
    public void refreshAllData() {
        loadAllRooms();
        loadAllBookings();
        JOptionPane.showMessageDialog(adminView, "Data refreshed", "Refresh", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Get selected booking ID from table
    public String getSelectedBookingId(JTable bookingsTable) {
        int selectedRow = bookingsTable.getSelectedRow();
        if (selectedRow >= 0) {
            return (String) bookingsTableModel.getValueAt(selectedRow, 0);
        }
        return null;
    }
    
    // Get selected room number from table
    public String getSelectedRoomNumber(JTable roomsTable) {
        int selectedRow = roomsTable.getSelectedRow();
        if (selectedRow >= 0) {
            return (String) roomsTableModel.getValueAt(selectedRow, 0);
        }
        return null;
    }
}