/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventregsystem;

/**
 *
 * @author 91953
 */
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private String userType;
    private JButton backButton;

    public LoginForm(String userType) {
        this.userType = userType;

        setTitle("Login - " + userType);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 100, 30);
        usernameField = new JTextField(20);
        usernameField.setBounds(150, 50, 200, 30);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 30);
        passwordField = new JPasswordField(20);
        passwordField.setBounds(150, 100, 200, 30);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(150, 150, 100, 30);
        
        backButton = new JButton("Back");
        backButton.setBounds(50, 150, 80, 30); // Adjust position as needed


        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(backButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginUser();
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new WelcomePage().setVisible(true);
                dispose(); // Close the current LoginForm window
            }
        });
    }

    private void loginUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        UserDao userDAO = new UserDao();

        if (userDAO.loginUser(username, password)) {
            String role = userDAO.getUserRole(username);

            if (role.equals(userType)) {
                JOptionPane.showMessageDialog(this, "Login successful!");

                int userId = userDAO.getUserId(username);

                if (userType.equals("Admin")) {
                    AdminDashboard adminDashboard = new AdminDashboard();
                    adminDashboard.setVisible(true);
                } else {
                    UserDashboard userDashboard = new UserDashboard(userId, userType, username);
                    userDashboard.setVisible(true);
                }
                
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Incorrect role selected. Please try again.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.");
        }
    }
}