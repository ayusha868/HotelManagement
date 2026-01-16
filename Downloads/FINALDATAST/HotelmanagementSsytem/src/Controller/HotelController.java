/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;


/**
 * Controller layer for the Hotel Management System
 */
import Model.*;
import java.util.*;

/**
 * Controller layer for the Hotel Management System
 * Acts as a bridge between the View (GUI) and the Model (DSA structures)
 */
public class HotelController {
    private static HotelModel model;

    public HotelController() throws ValidationException {
        this.model = new HotelModel();
    }

    // --- AUTHENTICATION ---
    /**
     * Checks credentials against the HashMap in HotelModel
     */
    public User login(String username, String password) throws Exception {
        User user = model.getUsers().get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        throw new Exception("Authentication Error: Invalid login credentials.");
    }

    // --- DATA RETRIEVAL ---
    /**
     * Converts HashMap values to an ArrayList for JTable display
     */
    public List<Room> getAllRooms() {
        return new ArrayList<>(model.getRooms().values());
    }

    /**
     * Returns the LinkedList containing booking history
     */
    public List<Booking> getBookingHistory() {
        return model.getHistory();
    }
    
    /**
     * Returns the size of the Stack (AdminRequests)
     */
    public int getPendingRequestCount() {
        return model.getAdminRequests().size();
    }

    // --- DSA: SELECTION SORT (By Price) ---
    /**
     * Sorts rooms by price using Selection Sort algorithm
     */
    public void selectionSortByPrice(ArrayList<Room> list, boolean ascending) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int targetIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (ascending ? list.get(j).getPricePerNight() < list.get(targetIdx).getPricePerNight() :
                                list.get(j).getPricePerNight() > list.get(targetIdx).getPricePerNight()) {
                    targetIdx = j;
                }
            }
            Collections.swap(list, i, targetIdx);
        }
    }

    // --- DSA: MERGE SORT (By Room Number) ---
    /**
     * Sorts rooms by Room ID using Merge Sort algorithm
     */
    public List<Room> mergeSortByRoomID(List<Room> list) {
        if (list.size() <= 1) return list;
        int mid = list.size() / 2;
        List<Room> left = mergeSortByRoomID(new ArrayList<>(list.subList(0, mid)));
        List<Room> right = mergeSortByRoomID(new ArrayList<>(list.subList(mid, list.size())));
        return merge(left, right);
    }

    private List<Room> merge(List<Room> left, List<Room> right) {
        List<Room> result = new ArrayList<>();
        int i = 0, j = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).getRoomNumber() < right.get(j).getRoomNumber()) {
                result.add(left.get(i++));
            } else {
                result.add(right.get(j++));
            }
        }
        result.addAll(left.subList(i, left.size()));
        result.addAll(right.subList(j, right.size()));
        return result;
    }

    // --- DSA: BINARY SEARCH ---
    /**
     * Searches for a specific room number using Binary Search
     */
    public Room searchRoomBinary(int roomNo) {
        ArrayList<Room> list = new ArrayList<>(model.getRooms().values());
        Collections.sort(list); // Room class implements Comparable by roomNumber
        int low = 0, high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            int midVal = list.get(mid).getRoomNumber();
            if (midVal == roomNo) return list.get(mid);
            if (midVal < roomNo) low = mid + 1;
            else high = mid - 1;
        }
        return null;
    }

    // --- DSA: LINEAR SEARCH ---
    /**
     * Finds rooms within a specific price range using Linear Search
     */
    public ArrayList<Room> linearSearchPriceRange(double min, double max) {
        ArrayList<Room> result = new ArrayList<>();
        for (Room r : model.getRooms().values()) {
            if (r.getPricePerNight() >= min && r.getPricePerNight() <= max) {
                result.add(r);
            }
        }
        return result;
    }

    // --- BOOKING ENGINE ---
    /**
     * Handles the creation of a booking and updates all DSA structures
     */
    public void createBooking(String name, String phone, int roomNo, Date start, Date end) throws Exception {
        Room room = model.getRooms().get(roomNo);
        if (room == null || !room.getStatus().equalsIgnoreCase("Available")) {
            throw new Exception("Room " + roomNo + " is not available for booking.");
        }

        // Logic: Calculate total based on room price and dates
        long diff = end.getTime() - start.getTime();
        int days = (int) (diff / (1000 * 60 * 60 * 24));
        if (days < 1) days = 1; // Minimum 1 day stay
        double totalPrice = room.getPricePerNight() * days;

        // Create new booking object
        Booking newBooking = new Booking(name, phone, roomNo, start, end, totalPrice, "Confirmed");
        newBooking.setId(model.generateBookingId());

        // Update Data Structures in Model
        model.getHistory().add(newBooking);         // LinkedList (History)
        model.getAdminRequests().push(newBooking);    // Stack (LIFO)
        model.getProcessingQueue().add(newBooking);   // Queue (FIFO)
        
        // Update room status
        room.setStatus("Booked");
    }
    public void addBooking(Booking b) {
    model.getHistory().add(b); // Accesses the LinkedList in the model
}

    /**
     * Accessor for the underlying model if needed by views
     */
    public HotelModel getModel() { 
        return model; 
    }
}