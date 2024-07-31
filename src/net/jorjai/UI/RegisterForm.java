package net.jorjai.UI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class RegisterForm extends JFrame {
    private JPanel cp;

    private JLabel titleLabel;

    private JLabel firstNameLabel;
    private JTextField firstNameField;

    private JLabel lastNameLabel;
    private JTextField lastNameField;

    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel emailErrorLabel;

    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JLabel passwordErrorLabel;

    private JLabel confirmPasswordLabel;
    private JPasswordField confirmPasswordField;
    private JLabel confirmPasswordErrorLabel;

    private JButton registerButton;
    private JLabel clickableLabel;
    private JLabel registerErrorLabel;

    public RegisterForm() {

        initialize();
    }

    private void initialize() {

        // Properties of the frame
        this.setTitle("Register - Hotel Management");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setPreferredSize(new Dimension(500, 600));
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

        // First name label
        firstNameLabel = new JLabel("First Name");
        firstNameLabel.setPreferredSize(new Dimension(190, 13));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 10);
        cp.add(firstNameLabel, gbc);

        // First name text field
        firstNameField = new JTextField();
        firstNameField.setPreferredSize(new Dimension(190, 30));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 0, 10, 10);
        cp.add(firstNameField, gbc);

        // Last name label
        lastNameLabel = new JLabel("Last Name");
        lastNameLabel.setPreferredSize(new Dimension(190, 13));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 10, 10, 0);
        cp.add(lastNameLabel, gbc);

        // Last name text field
        lastNameField = new JTextField();
        lastNameField.setPreferredSize(new Dimension(190, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(0, 10, 10, 0);
        cp.add(lastNameField, gbc);

        // Email label
        emailLabel = new JLabel("Email");
        emailLabel.setPreferredSize(new Dimension(400, 13));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(emailLabel, gbc);

        // Email text field
        emailField = new JTextField();
        emailField.setPreferredSize(new Dimension(400, 30));
        emailField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                emailErrorLabel.setVisible(false);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(emailField, gbc);

        // Email error label
        emailErrorLabel = new JLabel("Invalid email");
        emailErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        emailErrorLabel.setPreferredSize(new Dimension(400, 13));
        emailErrorLabel.setForeground(Color.RED);
        emailErrorLabel.setVisible(false);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(emailErrorLabel, gbc);

        // Password label
        passwordLabel = new JLabel("Password");
        passwordLabel.setPreferredSize(new Dimension(400, 13));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(passwordLabel, gbc);

        // Password field
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(400, 30));
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                passwordErrorLabel.setVisible(false);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(passwordField, gbc);

        // Password error label
        passwordErrorLabel = new JLabel("Password must be at least 8 characters long");
        passwordErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordErrorLabel.setPreferredSize(new Dimension(400, 13));
        passwordErrorLabel.setForeground(Color.RED);
        passwordErrorLabel.setVisible(false);
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(passwordErrorLabel, gbc);

        // Confirm password label
        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setPreferredSize(new Dimension(400, 13));
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(confirmPasswordLabel, gbc);

        // Confirm password field
        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setPreferredSize(new Dimension(400, 30));
        confirmPasswordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                confirmPasswordErrorLabel.setVisible(false);
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(confirmPasswordField, gbc);

        // Confirm password error label
        confirmPasswordErrorLabel = new JLabel("Passwords do not match");
        confirmPasswordErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        confirmPasswordErrorLabel.setPreferredSize(new Dimension(400, 13));
        confirmPasswordErrorLabel.setForeground(Color.RED);
        confirmPasswordErrorLabel.setVisible(false);
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(confirmPasswordErrorLabel, gbc);

        // Register button
        registerButton = new JButton("Register");
        registerButton.setPreferredSize(new Dimension(200, 30));
        registerButton.setFocusable(true);
        registerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        registerButton.addActionListener(e -> {
            // Check if the email is valid
            // Check if the password is at least 8 characters long
            // Check if the password and confirm password match
            // If all checks pass, register the user
            // If any check fails, show an error message
            emailErrorLabel.setVisible(true);
            passwordErrorLabel.setVisible(true);
            confirmPasswordErrorLabel.setVisible(true);
        });
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(registerButton, gbc);

        // Clickable label
        clickableLabel = new JLabel("<html><u>Already have an account? Login</u></html>");
        clickableLabel.setHorizontalAlignment(SwingConstants.CENTER);
        clickableLabel.setPreferredSize(new Dimension(400, 13));
        clickableLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        clickableLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Open the login form
                LoginForm loginForm = new LoginForm();
                loginForm.setVisible(true);
                dispose();
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 13;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(clickableLabel, gbc);

        // Register error label
        registerErrorLabel = new JLabel("An unexpected error occurred while registering");
        registerErrorLabel.setHorizontalAlignment(SwingConstants.CENTER);
        registerErrorLabel.setPreferredSize(new Dimension(400, 13));
        registerErrorLabel.setForeground(Color.RED);
        registerErrorLabel.setVisible(false);
        gbc.gridx = 0;
        gbc.gridy = 14;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        cp.add(registerErrorLabel, gbc);

        // Open the window
        setVisible(true);
    }

}
