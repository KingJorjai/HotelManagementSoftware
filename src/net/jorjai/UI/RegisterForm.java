package net.jorjai.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginForm extends JFrame {
    private JPanel cp;
    private JLabel titleLabel;
    private JLabel usernameLabel;
    private JTextField textField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel clickableLabel;
    private JLabel errorMessage;

    public LoginForm() {

        initialize();
    }

    private void initialize() {

        // Properties of the frame
        this.setTitle("Login - Hotel Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(400, 500));
        this.pack();

        // Get the content pane of the JFrame
        cp = (JPanel) getContentPane();
        cp.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Center the window
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Layout manager
        cp.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Company name title
        titleLabel = new JLabel("Hotel Name");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 20, 0);
        cp.add(titleLabel, gbc);

        // Username label
        usernameLabel = new JLabel("Username");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(usernameLabel, gbc);

        // Username text field
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(200, 30));
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                errorMessage.setVisible(false);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(textField, gbc);

        // Password label
        passwordLabel = new JLabel("Password");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(passwordLabel, gbc);

        // Password field
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(200, 30));
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                errorMessage.setVisible(false);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(passwordField, gbc);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(200, 30));
        loginButton.setFocusable(true);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        addLoginButtonListener();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(loginButton, gbc);

        // Login error message text
        errorMessage = new JLabel("Invalid username or password");
        errorMessage.setForeground(Color.RED);
        errorMessage.setVisible(false);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(errorMessage, gbc);

        // 'I don't have an account' clickable text
        clickableLabel = new JLabel("<html><u>I don't have an account</u></html>");
        clickableLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(clickableLabel, gbc);


        // Open the window
        setVisible(true);
    }

    private void addLoginButtonListener() {
        loginButton.addActionListener(e -> {
            // Check if the username and password are correct
            // If correct, open the main window

            // If incorrect, show an error message
            errorMessage.setVisible(true);

        });
    }
}
