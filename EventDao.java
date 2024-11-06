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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventDao {

    // Method to add a new event
    public boolean addEvent(String eventName, java.sql.Date eventDate, String description) {
        String sql = "INSERT INTO events (event_name, event_date, event_description) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventName);
            stmt.setDate(2, eventDate);
            stmt.setString(3, description);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.err.println("Error adding event: " + e.getMessage());
            return false;
        }
    }

    // Method to delete an event by ID
    public boolean deleteEvent(int eventId) {
        String sql = "DELETE FROM events WHERE event_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, eventId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting event: " + e.getMessage());
            return false;
        }
    }

    // Method to update an event's details by ID
    public boolean updateEvent(int eventId, String eventName, java.sql.Date eventDate, String description) {
        String sql = "UPDATE events SET event_name = ?, event_date = ?, event_description = ? WHERE event_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, eventName);
            stmt.setDate(2, eventDate);
            stmt.setString(3, description);
            stmt.setInt(4, eventId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error updating event: " + e.getMessage());
            return false;
        }
    }

    // Method to retrieve all events
    public List<Event> getAllEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT event_id, event_name, event_date, event_description FROM events";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int eventId = rs.getInt("event_id");
                String eventName = rs.getString("event_name");
                Date eventDate = rs.getDate("event_date");
                String description = rs.getString("event_description");
                events.add(new Event(eventId, eventName, eventDate.toString(), description));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving all events: " + e.getMessage());
        }
        return events;
    }

    // Method to retrieve a single event by its ID
    public Event getEventById(int eventId) {
        String sql = "SELECT event_id, event_name, event_date, event_description FROM events WHERE event_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, eventId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String eventName = rs.getString("event_name");
                    Date eventDate = rs.getDate("event_date");
                    String description = rs.getString("event_description");
                    return new Event(eventId, eventName, eventDate.toString(), description);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving event by ID: " + e.getMessage());
        }
        return null;
    }

    // Method to retrieve upcoming events
    public List<Event> getUpcomingEvents() {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT event_id, event_name, event_date, event_description FROM events WHERE event_date >= CURDATE() ORDER BY event_date";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int eventId = rs.getInt("event_id");
                String eventName = rs.getString("event_name");
                Date eventDate = rs.getDate("event_date");
                String description = rs.getString("event_description");
                events.add(new Event(eventId, eventName, eventDate.toString(), description));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving upcoming events: " + e.getMessage());
        }
        return events;
    }
}