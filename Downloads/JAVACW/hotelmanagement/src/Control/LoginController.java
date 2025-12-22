/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Control;

import Model.Auth;
import View.Login;
import View.UserDashboard;
import javax.swing.JOptionPane;

public class LoginController {

    public static void handleLogin(Login loginView) {
        String username = loginView.getUsername().trim();
        String password = loginView.getPassword();

        if (username.length() == 0 || password.length() == 0) {
            JOptionPane.showMessageDialog(loginView, "Please enter username and password");
            return;
        }

        if (loginView.isAdminSelected()) {
            // Simple admin check - you can improve this later
            if ("admin".equals(username) && "admin123".equals(password)) {
                JOptionPane.showMessageDialog(loginView, "Admin login successful (not implemented yet)");
                // You can open AdminDashboard here later
                loginView.dispose();
            } else {
                JOptionPane.showMessageDialog(loginView, "Invalid admin credentials");
            }
        } else {
            // Customer login - using username as customer identifier
            Auth.loginAsCustomer(username);
            JOptionPane.showMessageDialog(loginView, "Welcome " + username + "!");
            new UserDashboard().setVisible(true);
            loginView.dispose();
        }
    }
}