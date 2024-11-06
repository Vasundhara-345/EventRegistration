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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
     private static final String URL = "jdbc:mysql://localhost:3306/event_reg";
    private static final String username = "root"; // MySQL username
    private static final String password = ""; // MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, username, password);
    }
}
