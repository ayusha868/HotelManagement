/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author aayusharijal
 */
public class User {
    private final String username;
    private final String passwordHash;  // In real app use proper hashing
    private final boolean isAdmin;

    public User(String username, String password, boolean isAdmin) throws ValidationException {
        if (username == null || username.trim().isEmpty()) {
            throw new ValidationException("Username cannot be empty");
        }
        if (password == null || password.length() < 6) {
            throw new ValidationException("Password must be at least 6 characters");
        }

        this.username = username.trim();
        this.passwordHash = password; // simplified – in real app hash it
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean authenticate(String password) {
        return this.passwordHash.equals(password);
    }

    @Override
    public String toString() {
        return "User{" + "username='" + username + "', isAdmin=" + isAdmin + "}";
    }
}

