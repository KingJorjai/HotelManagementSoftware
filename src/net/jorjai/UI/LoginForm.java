package net.jorjai.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class LoginForm extends JFrame {
    private JPanel cp;
    private JLabel titleLabel;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel loginErrorLabel;
    private JLabel clickableLabel;

    public LoginForm() {

        initialize();
    }

    private void initialize() {

        // Properties of the frame
        this.setTitle("Login - Hotel Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(true);
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

        // Email label
        emailLabel = new JLabel("Email");
        emailLabel.setPreferredSize(new Dimension(200, 10));

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(emailLabel, gbc);

        // Username text field
        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(200, 30));
        emailField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                loginErrorLabel.setVisible(false);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(emailField, gbc);

        // Password label
        passwordLabel = new JLabel("Password");
        passwordLabel.setPreferredSize(new Dimension(200, 10));

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
                loginErrorLabel.setVisible(false);
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
        loginButton.addActionListener(e -> {
            // Check if the username and password are correct
            // If correct, open the main window

            // If incorrect, show an error message
            loginErrorLabel.setVisible(true);

        });
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(loginButton, gbc);

        // Login error message text
        loginErrorLabel = new JLabel("Invalid email or password");
        loginErrorLabel.setForeground(Color.RED);
        loginErrorLabel.setVisible(false);
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(loginErrorLabel, gbc);

        // 'I don't have an account' clickable text
        clickableLabel = new JLabel("<html><u>Not registered? Create an account</u></html>");
        clickableLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clickableLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clickableLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {

                // Open the registration form
                new RegisterForm();

                // Close the login form
                dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(clickableLabel, gbc);


        // Open the window
        setVisible(true);
    }
}
