/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phonebookapplication;

/**
 *
 * @author noura
 */
public class contact implements Comparable<contact> {

    private String name;
    private String PhoneNumber;
    private String Email;
    private String Address;
    private String Birthday;
    private String Notes;
    //an arttibute linkedlist for all types of events the contact have
    private Linkedlist<event> Events_Appointment;

    public contact(String name, String PhoneNumber, String Email, String Address, String Birthday, String Notes) {
        this.name = name;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.Address = Address;
        this.Birthday = Birthday;
        this.Notes = Notes;
        Events_Appointment = new Linkedlist<event>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String Birthday) {
        this.Birthday = Birthday;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String Notes) {
        this.Notes = Notes;
    }

    public String getFirstName() {
        //extract the space index from the contact name
        int spaceIndex = name.indexOf(" ");
        //check if the name have a space
        if (spaceIndex >= 0) {
            //retrun the firt name of the contact by substring
            return name.substring(0, spaceIndex);
        } else //if the contact name has no space it will return the full name
        {
            return name;
        }

    }

    public Linkedlist<event> getEvents() {
        return Events_Appointment;
    }

    public void setEvents(Linkedlist<event> Events) {
        this.Events_Appointment = Events;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" + "Phone Number: " + PhoneNumber + "\n" + "Email: " + Email + "\n" + "Address: " + Address + "\n" + "Birthday: " + Birthday + "\n" + "Notes: " + Notes;
    }

    //the method will print the contact info with the events title
    public void PrintWithEvent() {
        // check if the list is empty.
        if (!Events_Appointment.empty()) {
            // Find the first event.
            Events_Appointment.FindFirst();
            //print the contact data
            System.out.println(this.toString());
            // loop for printing the events title which is scheduled with the contact except the last event
            while (!Events_Appointment.Last()) {
                System.out.println("Event Title: " + Events_Appointment.Retrieve().getTitle());
                Events_Appointment.FindNext();
            }
            // printing the last events scheduled with the contact
            System.out.println("Event Title: " + Events_Appointment.Retrieve().getTitle());
        } //if the list is empty a message is appeared
        else {
            System.out.println("The Contact has no Events");
        }
    }

    //this method will add events in the Events List 
    public void addEvent(event data) {
        Events_Appointment.add(data);

    }

    // this method will retun a negtive number if name<c.name
    //ex: A<B
    // it will return a postive number if name>c.name 
    //ex: B<A
    // it will return 0 if name==c.name
    @Override
    public int compareTo(contact o) {
        return name.compareTo(o.name);
// Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
