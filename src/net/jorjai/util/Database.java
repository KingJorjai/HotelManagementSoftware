package net.jorjai.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {

        Connection cn;

        // Connect to embedded h2 database
        cn = DriverManager.getConnection("jdbc:h2:./data/db_hotel", "root", "");
            System.out.println("Connected to the database");
            return cn;

    }

    public static void resetDatabase() {
        try {
            Connection cn = getConnection();
            cn.createStatement().execute("DROP ALL OBJECTS");
            System.out.println("Database reset");
        } catch (SQLException e) {
            System.out.println("Error resetting the database");
        }
    }
}
