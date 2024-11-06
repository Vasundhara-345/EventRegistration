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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationDAO {

    public boolean addRegistration(int userId, int eventId, String status, String username, String eventName, String eventDate) {
        String sql = "INSERT INTO registrations (user_id, event_id, payment_status, username, event_name, event_date) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, eventId);
            stmt.setString(3, status);
            stmt.setString(4, username);
            stmt.setString(5, eventName);
            stmt.setDate(6, Date.valueOf(eventDate)); // Ensure eventDate is in "yyyy-MM-dd" format
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean isUserRegistered(int userId, int eventId) {
        String sql = "SELECT * FROM registrations WHERE user_id = ? AND event_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, eventId);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Returns true if a record is found, otherwise false
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}