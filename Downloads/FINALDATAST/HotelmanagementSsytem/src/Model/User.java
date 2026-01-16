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
    private String username, password, userType;
    public User(String username, String password, String userType) {
        this.username = username; this.password = password; this.userType = userType;
    }
    public String getUsername() { return username; }
    public void setUsername(String u) { this.username = u; }
    public String getPassword() { return password; }
    public void setPassword(String p) { this.password = p; }
    public String getUserType() { return userType; }
    public void setUserType(String t) { this.userType = t; }
}