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

public class SignUpForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private String userType;

    public SignUpForm(String userType) {
        this.userType = userType;

        setTitle("Sign Up - " + userType);
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(50, 50, 100, 30);
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 100, 100, 30);
        usernameField = new JTextField(20);
        usernameField.setBounds(150, 50, 200, 30);
        passwordField = new JPasswordField(20);
        passwordField.setBounds(150, 100, 200, 30);
        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(150, 150, 100, 30);

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(signupButton);

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        UserDao userDAO = new UserDao();

        if (userDAO.registerUser(username, password, userType)) {
            JOptionPane.showMessageDialog(this, "Registration successful! Please log in.");
            new LoginForm(userType).setVisible(true);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Registration failed. Please try again.");
        }
    }
}