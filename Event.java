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
public class Event {
    private int eventId;
    private String eventName;
    private String eventDate; // in "yyyy-MM-dd" format
    private String description;

    // Constructor with all fields
    public Event(int eventId, String eventName, String eventDate, String description) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.description = description;
    }

    // Getters
    public int getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return eventName + " (" + eventDate + ") - " + description;
    }
}