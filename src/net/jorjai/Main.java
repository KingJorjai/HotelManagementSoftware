package net.jorjai;

import net.jorjai.UI.Dashboard;
import net.jorjai.UI.LoginForm;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        // Set the look and feel of the application to the system's look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Check if the database has been initialized and initialize it if it hasn't
        try {
            if (!net.jorjai.util.Database.isInitialized()) {
                net.jorjai.util.Database.initialize();
            }
        } catch (SQLException e) {
            e.printStackTrace();

            // Show error dialog
            JOptionPane.showMessageDialog(null, "Error initializing the database", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // Show the login form
//        new LoginForm();

        // Debug only: open the main window directly
        Dashboard mw = new net.jorjai.UI.Dashboard(1);
    }
}