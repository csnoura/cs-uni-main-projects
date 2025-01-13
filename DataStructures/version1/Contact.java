/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phonebook;

/**
 *
 * @author noura
 */
public class Contact implements Comparable<Contact> {
    private String name;
    private String PhoneNumber;
    private String Email;
    private String Address;
    private String Birthday;
    private String Notes;
    private LinkedList<Event> Events;

    public Contact(String name, String PhoneNumber, String Email, String Address, String Birthday, String Notes) {
        this.name = name;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.Address = Address;
        this.Birthday = Birthday;
        this.Notes = Notes;
        Events = new LinkedList<Event>();
    }

    // this method will retun a negtive number if name<c.name
    //ex: A<B
    // it will return a postive number if name>c.name 
    //ex: B<A
    // it will return 0 if name==c.name
    public int compareTo(Contact c) {
        return name.compareTo(c.name);

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

    public LinkedList<Event> getEvents() {
        return Events;
    }

    public void setEvents(LinkedList<Event> Events) {
        this.Events = Events;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\n" + "Phone Number: " + PhoneNumber + "\n" + "Email: " + Email + "\n" + "Address: " + Address + "\n" + "Birthday: " + Birthday + "\n" + "Notes: " + Notes;
    }

    //the method will print the contact info with the events title
    public void PrintWithEvent() {
        // check if the list is empty.
        if (!Events.empty()) {
            // Find the first event.
            Events.FindFirst();
            //print the contact data
            System.out.println(this.toString());
            // loop for printing the events title which is scheduled with the contact except the last event
            while (!Events.Last()) {
                System.out.println("Event Title: " + Events.Retrieve().getTitle());
                Events.FindNext();
            }
            // printing the last events scheduled with the contact
            System.out.println("Event Title: " + Events.Retrieve().getTitle());
        } //if the list is empty a message is appeared
        else {
            System.out.println("The Contact has no Events");
        }
    }

    //this method will add events in the Events List 
    public void addEvent(Event data) {
        Events.add(data);

    }
}
