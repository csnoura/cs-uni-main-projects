/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phonebookapplication;

/**
 *
 * @author noura
 */

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template

/**
 *
 * @author noura
 */
public class event implements Comparable<event> {

    private String Title;
    private String Time;
    private String Date;
    private String Location;
    private contactBST Contacts;
    //an arttibute to determine the type of the event obj
    //event is type 2
    //appointment is type 1
    private int type;

    public event(int pickType, String Title, String Time, String Date, String Location) {
        if (pickType == 1) {
            type = 1;
        } else {
            type = 2;
        }
        this.Title = Title;
        this.Time = Time;
        this.Date = Date;
        this.Location = Location;
        Contacts = new contactBST();
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

    public contactBST getContacts() {
        return Contacts;
    }

    public void setContacts(contactBST Contacts) {
        this.Contacts = Contacts;
    }

    @Override
    public int compareTo(event e) {
        return Title.compareTo(e.Title);
    }

    @Override
    public String toString() {
        if (type == 2) {
            return "Event Title: " + Title + "\n" + "Time: " + Time + "\n" + "Date: " + Date + "\n" + "Location: " + Location;
        } else {
            return "Appointments Title: " + Title + "\n" + "Time: " + Time + "\n" + "Date: " + Date + "\n" + "Location: " + Location;
        }

    }

    public int getType() {
        return type;
    }

    //the method will print the event info with the contacts names
    public void PrintWithContact() {
        // check if the list is empty.   
        if (!Contacts.empty()) {
            //print the event info
            System.out.println(this.toString());
            //loop for printing the contact name which has a schedule event 
            Contacts.pprint(2);

        } else {//if the list is empty a message is appeared
            System.out.println("This event has no Contacts");
        }
    }
}
