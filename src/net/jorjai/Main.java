package net.jorjai;

import javax.swing.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        // Set the look and feel of the application to the system's look and feel
        try {
            UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
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
            JOptionPane.showMessageDialog(null, "Error initializing the database", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        }

        // Show the login form
        new net.jorjai.UI.LoginForm();

//        // Debug only: open the main window directly
//        MainWindow mw = new net.jorjai.UI.MainWindow(1);
    }
}