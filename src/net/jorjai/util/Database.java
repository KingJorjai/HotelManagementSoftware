package net.jorjai.util;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection getConnection() throws SQLException {

        Connection cn;

        // Connect to embedded h2 database
        cn = DriverManager.getConnection("jdbc:h2:./data/db_hotel", "root", "");
            System.out.println("Connected to the database");
            return cn;

    }

    public static void reset() {
        try {
            // Reset the database
            Connection cn = getConnection();
            cn.createStatement().execute("DROP ALL OBJECTS");

            // Show dialog
            JOptionPane.showMessageDialog(null, "Database reset successfully", "Success", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {

            // Show dialog
            JOptionPane.showMessageDialog(null, "Error resetting the database", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void initialize() {
        /*
        Staff: int StaffID (PK), varchar(50) FirstName, varchar(50) LastName, varchar(255) Email, varchar(255) Password, int PositionID (FK)
        Position: int PositionID (PK), varchar(50) Name, boolean canManageRooms, boolean canManageBookings, boolean canManageGuests, boolean canManageStaff, boolean canManagePayments
        Guest: int GuestID (PK), varchar(50) FirstName, varchar(50) LastName, date DateOfBirth, varchar(255) Address, varchar(255) Phone, varchar(255) Email
        Room: int RoomNumber (PK), int TypeID (FK), varchar(50) Status
        RoomType: int TypeID (PK), varchar(50) Name, varchar(255) Description, decimal(10, 2) PricePerNight, int Capacity
        Booking: int BookingID (PK), int RoomNumber (FK), int GuestID (FK), date CheckInDate, date CheckOutDate, decimal(10, 2) TotalPrice
        Payment: int PaymentID (PK), int BookingID (FK), date PaymentDate, decimal(10, 2) Amount, varchar(50) PaymentMethod
        */

        try {
            Connection cn = getConnection();
            cn.createStatement().execute("CREATE TABLE Position (PositionID INT PRIMARY KEY, Name VARCHAR(50), CanManageRooms BOOLEAN, CanManageBookings BOOLEAN, CanManageGuests BOOLEAN, CanManageStaff BOOLEAN, CanManagePayments BOOLEAN)");
            cn.createStatement().execute("CREATE TABLE Staff (StaffID INT PRIMARY KEY, FirstName VARCHAR(50), LastName VARCHAR(50), Email VARCHAR(255), Password VARCHAR(255), PositionID INT, FOREIGN KEY (PositionID) REFERENCES Position(PositionID))");
            cn.createStatement().execute("CREATE TABLE Guest (GuestID INT PRIMARY KEY, FirstName VARCHAR(50), LastName VARCHAR(50), DateOfBirth DATE, Address VARCHAR(255), Phone VARCHAR(255), Email VARCHAR(255))");
            cn.createStatement().execute("CREATE TABLE RoomType (TypeID INT PRIMARY KEY, Name VARCHAR(50), Description VARCHAR(255), PricePerNight DECIMAL(10, 2), Capacity INT)");
            cn.createStatement().execute("CREATE TABLE Room (RoomNumber INT PRIMARY KEY, TypeID INT, Status VARCHAR(50), FOREIGN KEY (TypeID) REFERENCES RoomType(TypeID))");
            cn.createStatement().execute("CREATE TABLE Booking (BookingID INT PRIMARY KEY, RoomNumber INT, GuestID INT, CheckInDate DATE, CheckOutDate DATE, TotalPrice DECIMAL(10, 2), FOREIGN KEY (RoomNumber) REFERENCES Room(RoomNumber), FOREIGN KEY (GuestID) REFERENCES Guest(GuestID))");
            cn.createStatement().execute("CREATE TABLE Payment (PaymentID INT PRIMARY KEY, BookingID INT, PaymentDate DATE, Amount DECIMAL(10, 2), PaymentMethod VARCHAR(50), FOREIGN KEY (BookingID) REFERENCES Booking(BookingID))");

            // Insert default position data
            cn.createStatement().execute("INSERT INTO Position VALUES (1, 'Manager', TRUE, TRUE, TRUE, TRUE, TRUE)");
            cn.createStatement().execute("INSERT INTO Position VALUES (2, 'Receptionist', TRUE, TRUE, TRUE, FALSE, TRUE)");
            cn.createStatement().execute("INSERT INTO Position VALUES (3, 'Housekeeper', TRUE, FALSE, FALSE, FALSE, FALSE)");

            // Insert default room type data
            cn.createStatement().execute("INSERT INTO RoomType VALUES (1, 'Single', 'A room with a single bed', 50.00, 1)");
            cn.createStatement().execute("INSERT INTO RoomType VALUES (2, 'Double', 'A room with a double bed', 75.00, 2)");
            cn.createStatement().execute("INSERT INTO RoomType VALUES (3, 'Suite', 'A room with a double bed and a living room', 100.00, 4)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
