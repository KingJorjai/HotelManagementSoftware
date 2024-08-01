package net.jorjai.UI;

import javax.swing.*;
import java.awt.*;

public class Dashboard extends JFrame {

    // Session variables
    private int StaffID;
    private String FirstName;
    private String LastName;
    private boolean canManageRooms;
    private boolean canManageBookings;
    private boolean canManageGuests;
    private boolean canManageStaff;
    private boolean canManagePayments;

    // Swing components
    private JPanel cp;
    private TopPanel topPanel;
    private CenterPanel centerPanel;
    private LeftPanel leftPanel;

    private class TopPanel extends JPanel {
        private JLabel titleLabel;
        private JLabel nameLabel;
        private JButton logoutButton;

        public TopPanel() {
            initialize();
            addListeners();
            setVisible(true);
        }

        public void initialize() {
            // Layout manager
            this.setLayout(new BorderLayout());
            // Line bottom border
            this.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

            // Company name title
            titleLabel = new JLabel("Hotel Name");
            titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
            this.add(titleLabel, BorderLayout.CENTER);

            JPanel topRightPanel = new JPanel();

            // Staff name
            nameLabel = new JLabel(FirstName + " " + LastName);
            topRightPanel.add(nameLabel, BorderLayout.EAST);

            // Logout button
            logoutButton = new JButton("Logout");
            logoutButton.setFocusable(false);
            topRightPanel.add(logoutButton, BorderLayout.EAST);

            this.add(topRightPanel, BorderLayout.EAST);
        }

        public void addListeners() {
            logoutButton.addActionListener(e -> {
                // Show the login form
                new LoginForm();
                // Close the current window
                dispose();
            });
        }
    }

    private class CenterPanel extends JPanel {
        public CenterPanel() {
            initialize();
            addListeners();
            setVisible(true);
        }

        public void initialize() {
            // Layout manager
            CardLayout cardLayout = new CardLayout();
            this.setLayout(cardLayout);

            // Center panel
            JLabel centerLabel = new JLabel("Welcome to the Hotel Management System");
            centerLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(centerLabel, "dashboard");
            cardLayout.show(this, "dashboard");

        }

        public void addListeners() {
            // Add listeners here
        }
    }
    private class LeftPanel extends JPanel {
        JButton showHideButton;
        JPanel menuPanel;
        JButton roomsButton;
        JButton bookingsButton;
        JButton guestsButton;
        JButton staffButton;
        JButton paymentsButton;

        public LeftPanel() {
            initialize();
            addListeners();
            setVisible(true);
        }

        public void initialize() {
            // Layout manager
            this.setLayout(new BorderLayout());
            // Line right border
            this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.BLACK));

            // ShowHide Button
            showHideButton = new JButton("<");
            showHideButton.setFocusable(false);
            this.add(showHideButton, BorderLayout.EAST);

            // Menu Panel
            menuPanel = new JPanel();
            menuPanel.setLayout(new GridLayout(0, 1));

            // Rooms Button
            roomsButton = new JButton("Rooms");
            roomsButton.setFocusable(false);
            menuPanel.add(roomsButton);

            // Bookings Button
            bookingsButton = new JButton("Bookings");
            bookingsButton.setFocusable(false);
            menuPanel.add(bookingsButton);

            // Guests Button
            guestsButton = new JButton("Guests");
            guestsButton.setFocusable(false);
            menuPanel.add(guestsButton);

            // Staff Button
            staffButton = new JButton("Staff");
            staffButton.setFocusable(false);
            menuPanel.add(staffButton);

            // Payments Button
            paymentsButton = new JButton("Payments");
            paymentsButton.setFocusable(false);
            menuPanel.add(paymentsButton);

            this.add(menuPanel, BorderLayout.CENTER);
        }

        public void addListeners() {
            // ShowHide Button listener
            showHideButton.addActionListener(e -> {
                if (menuPanel.isVisible()) {
                    menuPanel.setVisible(false);
                    showHideButton.setText(">");
                } else {
                    menuPanel.setVisible(true);
                    showHideButton.setText("<");
                }
            });

            // Rooms Button listener
            roomsButton.addActionListener(e -> {
                CardLayout cardLayout = (CardLayout) centerPanel.getLayout();
                cardLayout.show(centerPanel, "rooms");
            });
        }
    }

    public Dashboard(int StaffID) {
        this.StaffID = StaffID;
        new Dashboard();
    }

    private void retreiveSessionData(int staffID) {
        // Get the staff data from the database
        // Set the session variables

        // Debug only: set the session variables
        this.StaffID = 1;
        this.FirstName = "John";
        this.LastName = "Doe";
        this.canManageRooms = true;
        this.canManageBookings = true;
        this.canManageGuests = true;
        this.canManageStaff = true;
        this.canManagePayments = true;

    }

    private Dashboard() {
        retreiveSessionData(StaffID);
        initialize();
        addListeners();
        setVisible(true);
    }

    private void initialize() {

        // Properties of the frame
        this.setTitle("Dashboard - Hotel Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
        this.setPreferredSize(new java.awt.Dimension(720, 540));
        this.pack();

        // Get the content pane of the JFrame
        cp = (JPanel) getContentPane();
        cp.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Center the window
        java.awt.Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

        // Layout manager
        cp.setLayout(new BorderLayout());
        topPanel = new TopPanel();
        cp.add(topPanel, BorderLayout.NORTH);
        centerPanel = new CenterPanel();
        cp.add(centerPanel, BorderLayout.CENTER);
        leftPanel = new LeftPanel();
        cp.add(leftPanel, BorderLayout.WEST);

    }

    private void addListeners() {

    }
}
