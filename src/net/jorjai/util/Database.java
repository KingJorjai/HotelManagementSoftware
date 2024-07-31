package net.jorjai.util;

import javax.swing.*;
import java.sql.*;

public class Database {

    /**
     * Get a connection to the database
     * @return The connection to the database
     * @throws SQLException If an error occurs while connecting to the database
     */
    public static Connection getConnection() throws SQLException {

        Connection cn;

        // Connect to embedded h2 database
        cn = DriverManager.getConnection("jdbc:h2:./data/db_hotel", "root", "");
            return cn;

    }

    /**
     * Reset the database by dropping all tables
     * @throws SQLException If an error occurs while executing the query
     */
    public static void reset() throws SQLException {

        // Reset the database
        Connection cn = getConnection();
        cn.createStatement().execute("DROP ALL OBJECTS");
        cn.close();
    }

    /**
     * Initialize the database with the required tables and default data
     *  Staff: int StaffID (PK), varchar(50) FirstName, varchar(50) LastName, varchar(255) Email, varchar(255) Password, int PositionID (FK)
     *  Position: int PositionID (PK), varchar(50) Name, boolean canManageRooms, boolean canManageBookings, boolean canManageGuests, boolean canManageStaff, boolean canManagePayments
     *  Guest: int GuestID (PK), varchar(50) FirstName, varchar(50) LastName, date DateOfBirth, varchar(255) Address, varchar(255) Phone, varchar(255) Email
     *  Room: int RoomNumber (PK), int TypeID (FK), varchar(50) Status
     *  RoomType: int TypeID (PK), varchar(50) Name, varchar(255) Description, decimal(10, 2) PricePerNight, int Capacity
     *  Booking: int BookingID (PK), int RoomNumber (FK), int GuestID (FK), date CheckInDate, date CheckOutDate, decimal(10, 2) TotalPrice
     *  Payment: int PaymentID (PK), int BookingID (FK), date PaymentDate, decimal(10, 2) Amount, varchar(50) PaymentMethod
     *  IDs are auto-incremented
     *
     * @throws SQLException If an error occurs while executing the query
     */
    public static void initialize() throws SQLException {

        Connection cn = getConnection();
        cn.createStatement().execute("CREATE TABLE Position (PositionID INT AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(50), CanManageRooms BOOLEAN, CanManageBookings BOOLEAN, CanManageGuests BOOLEAN, CanManageStaff BOOLEAN, CanManagePayments BOOLEAN)");
        cn.createStatement().execute("CREATE TABLE Staff (StaffID INT AUTO_INCREMENT PRIMARY KEY, FirstName VARCHAR(50), LastName VARCHAR(50), Email VARCHAR(255), Password VARCHAR(255), PositionID INT, FOREIGN KEY (PositionID) REFERENCES Position(PositionID))");
        cn.createStatement().execute("CREATE TABLE Guest (GuestID INT AUTO_INCREMENT PRIMARY KEY, FirstName VARCHAR(50), LastName VARCHAR(50), DateOfBirth DATE, Address VARCHAR(255), Phone VARCHAR(255), Email VARCHAR(255))");
        cn.createStatement().execute("CREATE TABLE RoomType (TypeID INT AUTO_INCREMENT PRIMARY KEY, Name VARCHAR(50), Description VARCHAR(255), PricePerNight DECIMAL(10, 2), Capacity INT)");
        cn.createStatement().execute("CREATE TABLE Room (RoomNumber INT AUTO_INCREMENT PRIMARY KEY, TypeID INT, Status VARCHAR(50), FOREIGN KEY (TypeID) REFERENCES RoomType(TypeID))");
        cn.createStatement().execute("CREATE TABLE Booking (BookingID INT AUTO_INCREMENT PRIMARY KEY, RoomNumber INT, GuestID INT, CheckInDate DATE, CheckOutDate DATE, TotalPrice DECIMAL(10, 2), FOREIGN KEY (RoomNumber) REFERENCES Room(RoomNumber), FOREIGN KEY (GuestID) REFERENCES Guest(GuestID))");
        cn.createStatement().execute("CREATE TABLE Payment (PaymentID INT AUTO_INCREMENT PRIMARY KEY, BookingID INT, PaymentDate DATE, Amount DECIMAL(10, 2), PaymentMethod VARCHAR(50), FOREIGN KEY (BookingID) REFERENCES Booking(BookingID))");

        // Insert default positions
        PreparedStatement ps = cn.prepareStatement("INSERT INTO Position (Name, CanManageRooms, CanManageBookings, CanManageGuests, CanManageStaff, CanManagePayments) VALUES (?, ?, ?, ?, ?, ?)");

        ps.setString(1, "Unverified");
        ps.setBoolean(2, false); // Can manage rooms
        ps.setBoolean(3, false); // Can manage bookings
        ps.setBoolean(4, false); // Can manage guests
        ps.setBoolean(5, false); // Can manage staff
        ps.setBoolean(6, false); // Can manage payments
        ps.executeUpdate();

        ps.setString(1, "Manager");
        ps.setBoolean(2, true); // Can manage rooms
        ps.setBoolean(3, true); // Can manage bookings
        ps.setBoolean(4, true); // Can manage guests
        ps.setBoolean(5, true); // Can manage staff
        ps.setBoolean(6, true); // Can manage payments
        ps.executeUpdate();

        ps.setString(1, "Receptionist");
        ps.setBoolean(2, true); // Can manage rooms
        ps.setBoolean(3, true); // Can manage bookings
        ps.setBoolean(4, true); // Can manage guests
        ps.setBoolean(5, false); // Can manage staff
        ps.setBoolean(6, true); // Can manage payments
        ps.executeUpdate();

        ps.setString(1, "Housekeeper");
        ps.setBoolean(2, true); // Can manage rooms
        ps.setBoolean(3, false); // Can manage bookings
        ps.setBoolean(4, false); // Can manage guests
        ps.setBoolean(5, false); // Can manage staff
        ps.setBoolean(6, false); // Can manage payments
        ps.executeUpdate();

        // Close the connection
        cn.close();
    }

    /**
     * Register a new staff member
     * @param firstName The first name of the staff member
     * @param lastName The last name of the staff member
     * @param email The email of the staff member
     * @param password The password of the staff member
     * @throws SQLException If an error occurs while executing the query
     */
    public static void registerStaff(String firstName, String lastName, String email, String password) throws SQLException {
        Connection cn = Database.getConnection();

        // Insert the new staff member
        PreparedStatement ps = cn.prepareStatement("INSERT INTO Staff (FirstName, LastName, Email, Password, PositionID) VALUES (?, ?, ?, ?, ?)");
        ps.setString(1, firstName);
        ps.setString(2, lastName);
        ps.setString(3, email);
        ps.setString(4, password);
        if (isTableEmpty("Staff")) {
            ps.setInt(5, 1); // Manager
        } else {
            ps.setInt(5, 0); // Unverified
        }
        ps.setInt(5, 2);
        ps.executeUpdate();

        // Close the connection
        cn.close();
    }

    /**
     * Login a staff member using their email and password and return their ID
     * @param email The email of the staff member
     * @param password The password of the staff member
     * @return The ID of the staff member if the login is successful, -1 otherwise
     * @throws SQLException If an error occurs while executing the query
     */
    public static int loginStaff(String email, String password) throws SQLException {
        Connection cn = Database.getConnection();
        PreparedStatement ps = cn.prepareStatement("SELECT StaffID FROM Staff WHERE Email = ? AND Password = ?");
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        int staffID = -1;
        if (rs.next()) {
            staffID = rs.getInt(1);
        }
        cn.close();
        return staffID;
    }

    /**
     * Check if a table is empty
     * @param tableName The name of the table
     * @return True if the table is empty, false otherwise
     * @throws SQLException If an error occurs while executing the query
     */
    public static boolean isTableEmpty(String tableName) throws SQLException {
        Connection cn = Database.getConnection();
        PreparedStatement ps = cn.prepareStatement("SELECT COUNT(*) FROM " + tableName);
        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        cn.close();
        return count == 0;
    }

    /**
     * Check if the tables are initialized
     */
    public static boolean isInitialized() {
        try {
            Connection cn = Database.getConnection();
            cn.createStatement().execute("SELECT * FROM Position");
            cn.createStatement().execute("SELECT * FROM Staff");
            cn.createStatement().execute("SELECT * FROM Guest");
            cn.createStatement().execute("SELECT * FROM RoomType");
            cn.createStatement().execute("SELECT * FROM Room");
            cn.createStatement().execute("SELECT * FROM Booking");
            cn.createStatement().execute("SELECT * FROM Payment");
            cn.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
