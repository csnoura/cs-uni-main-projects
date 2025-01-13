/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phonebook;

/**
 *
 * @author noura
 */

public class Event implements Comparable<Event> {

    private String Title;
    private String Time;
    private String Date;
    private String Location;
    private LinkedList<Contact> Contacts;

    public Event(String Title, String Time, String Date, String Location) {
        this.Title = Title;
        this.Time = Time;
        this.Date = Date;
        this.Location = Location;
        Contacts = new LinkedList<Contact>();
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String Time) {
        this.Time = Time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    public LinkedList<Contact> getContacts() {
        return Contacts;
    }

    public void setContacts(LinkedList<Contact> Contacts) {
        this.Contacts = Contacts;
    }

    @Override
    public int compareTo(Event e) {
        return Title.compareTo(e.Title);
    }

    @Override
    public String toString() {
        return "Event Title: " + Title + "\n" + "Time: " + Time + "\n" + "Date: " + Date + "\n" + "Location: " + Location;

    }

    //the method will print the event info with the contact name
    public void PrintWithContact() {
        // check if the list is empty.   
        if (!Contacts.empty()) {
            // Find the first contact.
            Contacts.FindFirst();
            //print the event data
            System.out.println(this.toString());
            //loop for printing the contact name which has a schedule event except the last contact
            while (!Contacts.Last()) {
                System.out.println("Contact name: " + Contacts.Retrieve().getName());
                Contacts.FindNext();
            }
            //printing the last contact name
            System.out.println("Contact name: " + Contacts.Retrieve().getName());
        } //if the list is empty a message is appeared
        else {
            System.out.println("This event has no Contacts with it");
        }
    }
}
