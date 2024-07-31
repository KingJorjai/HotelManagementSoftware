package net.jorjai.UI;

import javax.swing.*;

public class Dashboard extends JFrame {

    // Session variables
    private int StaffID;

    // Swing components
    private JPanel cp;

    public Dashboard(int StaffID) {
        this.StaffID = StaffID;
        new Dashboard();
    }

    private Dashboard() {
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


    }

    private void addListeners() {

    }
}
