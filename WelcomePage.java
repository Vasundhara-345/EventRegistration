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

public class WelcomePage extends JFrame {
    private JComboBox<String> userTypeComboBox;
    private JButton loginButton;
    private JButton signupButton;

    public WelcomePage() {
        setTitle("Event Registration System");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("EVENT REGISTRATION SYSTEM", SwingConstants.CENTER);
        titleLabel.setFont(new java.awt.Font("Arial", java.awt.Font.BOLD, 20));
        titleLabel.setBounds(0, 20, 400, 30);

        JLabel welcomeLabel = new JLabel("Welcome! Please select user type and choose an option.", SwingConstants.CENTER);
        welcomeLabel.setBounds(0, 60,400, 30);

        userTypeComboBox = new JComboBox<>(new String[] {"Student", "Staff", "Admin"});
        userTypeComboBox.setBounds(150, 100, 100, 30);

        loginButton = new JButton("Login");
        loginButton.setBounds(100, 150, 80, 30);

        signupButton = new JButton("Sign Up");
        signupButton.setBounds(220, 150, 80, 30);

        add(titleLabel);
        add(welcomeLabel);
        add(userTypeComboBox);
        add(loginButton);
        add(signupButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginPage();
            }
        });

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSignupPage();
            }
        });
    }

    private void openLoginPage() {
        String userType = (String) userTypeComboBox.getSelectedItem();
        new LoginForm(userType).setVisible(true);
        dispose();
    }

    private void openSignupPage() {
        String userType = (String) userTypeComboBox.getSelectedItem();
        new SignUpForm(userType).setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        new WelcomePage().setVisible(true);
    }
}