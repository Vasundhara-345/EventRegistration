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
import java.sql.Date;
import java.util.List;

public class AdminDashboard extends JFrame {
    private JComboBox<Event> eventComboBox;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton viewAllButton;
    private JButton backButton;

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        eventComboBox = new JComboBox<>();
        eventComboBox.setBounds(50, 30, 400, 30);
        addButton = new JButton("Add Event");
        addButton.setBounds(50, 80, 120, 30);
        updateButton = new JButton("Update Event");
        updateButton.setBounds(180, 80, 120, 30);
        deleteButton = new JButton("Delete Event");
        deleteButton.setBounds(310, 80, 120, 30);
        viewAllButton = new JButton("View All Events");
        viewAllButton.setBounds(150, 130, 200, 30);
        backButton = new JButton("Back");
        backButton.setBounds(200,300,100,30);
        
        populateEvents();

        add(eventComboBox);
        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(viewAllButton);
        add(backButton);
        // Action listener for adding a new event
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eventName = JOptionPane.showInputDialog("Enter Event Name:");
                if (eventName == null || eventName.trim().isEmpty()) return;

                try {
                    Date eventDate = Date.valueOf(JOptionPane.showInputDialog("Enter Event Date (yyyy-mm-dd):"));
                    String description = JOptionPane.showInputDialog("Enter Description:");
                    EventDao eventDAO = new EventDao();
                    if (eventDAO.addEvent(eventName, eventDate, description)) {
                        JOptionPane.showMessageDialog(null, "Event added successfully!");
                        populateEvents();
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid date format. Please use yyyy-mm-dd.");
                }
            }
        });

        // Action listener for updating an event
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Event selectedEvent = (Event) eventComboBox.getSelectedItem();
                if (selectedEvent != null) {
                    String newEventName = JOptionPane.showInputDialog("Enter New Event Name:", selectedEvent.getEventName());
                    if (newEventName == null || newEventName.trim().isEmpty()) return;

                    try {
                        Date newEventDate = Date.valueOf(JOptionPane.showInputDialog("Enter New Event Date (yyyy-mm-dd):", selectedEvent.getEventDate().toString()));
                        String newDescription = JOptionPane.showInputDialog("Enter New Description:", selectedEvent.getDescription());
                        EventDao eventDAO = new EventDao();
                        if (eventDAO.updateEvent(selectedEvent.getEventId(), newEventName, newEventDate, newDescription)) {
                            JOptionPane.showMessageDialog(null, "Event updated successfully!");
                            populateEvents();
                        }
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(null, "Invalid date format. Please use yyyy-mm-dd.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No event selected.");
                }
            }
        });

        // Action listener for deleting an event
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Event selectedEvent = (Event) eventComboBox.getSelectedItem();
                if (selectedEvent != null) {
                    int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this event?");
                    if (confirm == JOptionPane.YES_OPTION) {
                        EventDao eventDAO = new EventDao();
                        if (eventDAO.deleteEvent(selectedEvent.getEventId())) {
                            JOptionPane.showMessageDialog(null, "Event deleted successfully!");
                            populateEvents();
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No event selected.");
                }
            }
        });

        // Action listener for viewing all events
        viewAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EventDao eventDAO = new EventDao();
                List<Event> events = eventDAO.getAllEvents();
                StringBuilder allEvents = new StringBuilder("All Events:\n");

                for (Event event : events) {
                    allEvents.append(event.toString()).append("\n");
                }

                JOptionPane.showMessageDialog(null, allEvents.toString());
            }
        });
        
        backButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                new WelcomePage().setVisible(true);
                dispose();
            }
        });
    }

    // Helper method to populate the combo box with events
    private void populateEvents() {
        eventComboBox.removeAllItems();
        EventDao eventDAO = new EventDao();
        List<Event> events = eventDAO.getAllEvents();
        for (Event event : events) {
            eventComboBox.addItem(event);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AdminDashboard().setVisible(true);
            }
        });
    }
}