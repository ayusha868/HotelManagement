/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author aayusharijal
 */
import java.util.*;

public class HotelModel {
    private HashMap<Integer, Room> rooms = new HashMap<>();
    private HashMap<String, User> users = new HashMap<>();
    private LinkedList<Booking> history = new LinkedList<>();
    private Stack<Booking> adminRequests = new Stack<>();
    private Queue<Booking> processingQueue = new LinkedList<>();
    private int idCounter = 1001;

    public HotelModel() throws ValidationException {
        seedData();
    }

    private void seedData() throws ValidationException {
        rooms.put(101, new Room(101, "Standard Single", 1, 50.0, "Available", 200, 1));
        rooms.put(102, new Room(102, "Standard Single", 1, 50.0, "Available", 200, 1));
        rooms.put(103, new Room(103, "Standard Double", 2, 100.0, "Available", 400, 2));
        rooms.put(104, new Room(104, "Standard Double", 2, 100.0, "Available", 400, 2));
        rooms.put(105, new Room(105, "Deluxe Room", 2, 150.0, "Available", 400, 2));
        rooms.put(106, new Room(106, "Deluxe Room", 2, 150.0, "Available", 400, 2));
        rooms.put(107, new Room(107, "Executive Suite", 2, 200.0, "Available", 400, 3));
        rooms.put(108, new Room(108, "Executive Suite", 2, 200.0, "Available", 400, 3));

        users.put("admin", new User("admin", "admin123", "Admin"));
        users.put("user", new User("user", "user123", "User"));
    }

    public HashMap<Integer, Room> getRooms() { return rooms; }
    public HashMap<String, User> getUsers() { return users; }
    public LinkedList<Booking> getHistory() { return history; }
    public Stack<Booking> getAdminRequests() { return adminRequests; }
    public Queue<Booking> getProcessingQueue() { return processingQueue; }
    public int generateBookingId() { return idCounter++; }
}