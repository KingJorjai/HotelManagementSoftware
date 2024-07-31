import net.jorjai.UI.LoginForm;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        // Set the look and feel of the application to the system's look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Reset database
        net.jorjai.util.Database.reset();

        // Initialize database
        net.jorjai.util.Database.initialize();

        // Show the login form
        new LoginForm();
    }
}