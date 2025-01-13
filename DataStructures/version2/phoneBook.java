/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phonebookapplication;

/**
 *
 * @author noura
 */
public class phoneBook {

    String name;
    //bst for all contacts
    contactBST Contacts;
    //list for all types of events
    Linkedlist<event> AllEvents_Appointment;

    public phoneBook(String name) {
        this.name = name;
        Contacts = new contactBST();
        AllEvents_Appointment = new Linkedlist<event>();
    }

    //method to add contact
    public void addContact(contact c) {
        //if false means the key or the phone number is not unique
        if (Contacts.insert(c) == false) {
            System.out.println("conatct is already exist");
        } else {
            System.out.println("contact is added successfully!");
        }

    }

    //method to print all the contacts info
    public void Printallcontact() {
        Contacts.pprint(1);
    }

    //method to search for contact who share the same data
    public void SearchContact(String data, int pick) {
        //if false , means that no match found
        if (Contacts.pSearchContact(data, pick) == false) {
            System.out.println("The value you wanted to search doesn't exist");
        }

    }

    //method to remove a contact from the contact bst
    public void RemoveConatct(String name) {
        //check if the tree has contact
        if (!Contacts.empty()) {
            //check if the contact exsits
            if (Contacts.findkey(name)) {
                //call method removeEvents to handle all cases to remove the contacts events
                RemoveEvents(Contacts.Retrieve());
                //remove the contact from the contactsBST
                Contacts.remove_key(name);
                System.out.println("Contact removed successfully!");
            }

        } else {
            System.out.println("there's no contact in the phonebook to delete");
        }
    }

    //--------------------------------------------------------------------------
    ///this methof 1-search for all the appointment that the contact has so it can be removed 
    //2-checks if the current event in the contact c events has only the contact c
    // case 1 if it associated only with c contact object ,case2 if it associated with muti contact object,
    //in the first case that means the event is acting like an appointment
    //so if it true then we will search for it in the AllEvents_Appointment list so it can be removed
    public void RemoveEvents(contact c) {
        Linkedlist<event> cEvents = c.getEvents();
        //set current to the first event in the contact c events
        cEvents.FindFirst();
        //check if the list is empty or not
        if (!cEvents.empty() && !AllEvents_Appointment.empty()) {
            //loop to search in contact c events
            while (!cEvents.Last()) {
                //set current of AllEvents_Appointment list at the event of the contact c by searching for it
                if (AllEvents_Appointment.Search(cEvents.Retrieve()) == true) {
                    //remove the contact c from the event contactBST
                    cEvents.Retrieve().getContacts().remove_key(c.getName());
                    //if the event is an appointment or it is an event but it has only contact c then we delete this event/appointment from the AllEvents_Appointment list
                    if (((AllEvents_Appointment.Retrieve().getType() == 2) && (cEvents.Retrieve().getContacts().empty())) || (AllEvents_Appointment.Retrieve().getType() == 1)) {
                        //remove the event/appointment
                        AllEvents_Appointment.Remove();
                    }

                }

                //move to the next event
                cEvents.FindNext();

            }
            //check the same thing but for the last event obj in the contact c events
            if (AllEvents_Appointment.Search(cEvents.Retrieve()) == true) {
                cEvents.Retrieve().getContacts().remove_key(c.getName());
                if (((AllEvents_Appointment.Retrieve().getType() == 2) && (cEvents.Retrieve().getContacts().empty())) || (AllEvents_Appointment.Retrieve().getType() == 1)) {
                    AllEvents_Appointment.Remove();
                }
            }

        } else {
            System.out.println("the contact has no events to remove or there's no events in this phonebook");
        }
    }

    //this method adds event/appointment in the AllEvents_Appointment list ordered alphabetically
    public void addEvents(event NewEvent) {
        // Create a temp Event variable.
        event temp;

        // Move current to the first event in the list.
        AllEvents_Appointment.FindFirst();
        // Check if the list is empty.
        if (AllEvents_Appointment.empty()) {
            // Add the new event to the list.
            AllEvents_Appointment.add(NewEvent);

        } else {
            //loop until we reach the last event
            while (!AllEvents_Appointment.Last()) {
                // Check if the new event title is smaller than the current event title in the list 
                //the new event should be inserted before the current event.
                if (AllEvents_Appointment.Retrieve().compareTo(NewEvent) > 0) {
                    // Store the first event title in the temp variable.
                    temp = AllEvents_Appointment.Retrieve();
                    // Update the the current node in the allEvents list with the new event(data).
                    AllEvents_Appointment.Update(NewEvent);
                    // Store the first event name in the temp variable.
                    AllEvents_Appointment.add(temp);
                    //break from the loop if we found the right place
                    break;
                }
                //move to the next event

                AllEvents_Appointment.FindNext();
            }
            // repeated code to check the last event if we reach it 

            if (AllEvents_Appointment.Last()) {
                if (AllEvents_Appointment.Retrieve().compareTo(NewEvent) > 0) {
                    temp = AllEvents_Appointment.Retrieve();
                    AllEvents_Appointment.Update(NewEvent);
                    AllEvents_Appointment.add(temp);

                } else //this for if the new event title is greater than the last title  of the last contact by ascii code
                {
                    AllEvents_Appointment.add(NewEvent);
                }

            }

        }
        System.out.println("An event is added successfully");
    }

    // this method Schedule Event/appointment with a contact
    public void ScheduleEvent(event newEvent, String contactName) {
        //boolean varible to show if a there is a conflict or not
        boolean conflictExists = false;
        //to assgin it with the contact object later to schedule event
        contact contact = null;
        //search for the contact obj by the name(key) also to make sure it exsits
        if (Contacts.findkey(contactName)) {
            //store the conatct obj is contact object
            contact = Contacts.Retrieve();

            //set current to the first event
            AllEvents_Appointment.FindFirst();
            //check if the list is not empty 
            if (!AllEvents_Appointment.empty()) {
                //loop until the last event
                while (!AllEvents_Appointment.Last()) {
                    //check if the time and date exists in allEvents list

                    if (AllEvents_Appointment.Retrieve().getTime().equals(newEvent.getTime()) && AllEvents_Appointment.Retrieve().getDate().equals(newEvent.getDate())) {
                        if (!AllEvents_Appointment.Retrieve().getTitle().equals(newEvent.getTitle())) //means there is a conflict, since the event dose not Exists in the allEvents list
                        {
                            conflictExists = true;
                        } else {
                            //add the contact in the contacts list attribute of newEvent object
                            newEvent.getContacts().insert(contact);
                            //add the newEvent in the Events list attribute of the contact object
                            contact.getEvents().add(newEvent);
                            System.out.println("A new event is added!");
                            //close the methode since the event already exists and found
                            return;
                        }
                    } else //move to the next event
                    {
                        AllEvents_Appointment.FindNext();
                    }
                }
                //check if the time and date exists in the last event in the list

                if (AllEvents_Appointment.Retrieve().getTime().equals(newEvent.getTime()) && AllEvents_Appointment.Retrieve().getDate().equals(newEvent.getDate())) {
                    if (!AllEvents_Appointment.Retrieve().getTitle().equals(newEvent.getTitle())) //means there is a conflict since the event dose not Exists in the allEvents list
                    {
                        conflictExists = true;
                    } else {
                        //add the contact in the Contacts BST attribute of newEvent object
                        contact.getEvents().add(newEvent);
                        //add the newEvent in the Events list attribute of the contact object
                        newEvent.getContacts().insert(contact);
                        System.out.println("A new event is added!");
                        //close the methode since the event already exists and found
                        return;
                    }
                }
                //check if there's a conflict 
                if (conflictExists == true) {
                    System.out.println("there's a conflict in Time");
                    //close the methode so we dont add the newEvent in the list
                    return;
                }
            }
            //add the newEvent in the AllEvents_Appointment list 
            addEvents(newEvent);
            //add the contact in the Contacts BST attribute of newEvent object
            newEvent.getContacts().insert(contact);
            //add the newEvent in the Events list attribute of the contact object
            contact.getEvents().add(newEvent);
            System.out.println("A new event is added!");

        } else {
            System.out.println("contact does not Exists");
        }
    }

    //print event detalis by title
    public void PrintByTitle(String title) {
        //set the current to the fisrt event
        AllEvents_Appointment.FindFirst();
        //check if the list is empty or not 
        if (!AllEvents_Appointment.empty()) {
            //loop to search in allEvents 
            while (!AllEvents_Appointment.Last()) {
                //check if the current event share the same title 
                if (AllEvents_Appointment.Retrieve().getTitle().equals(title)) {
                    //print the current event that share the same title 
                    AllEvents_Appointment.Retrieve().PrintWithContact();
                }
                //move to next event
                AllEvents_Appointment.FindNext();
            }
            //check if the last event share the same title 
            if (AllEvents_Appointment.Retrieve().getTitle().equals(title)) {
                //print the last event that share the same title 
                AllEvents_Appointment.Retrieve().PrintWithContact();
            }
        } else {
            System.out.println("the event list is empty");
        }
    }

    // method to print allEvents list and it will be printed ordered alphabetically
    // since allEvents is orderd
    public void PrintAllEvents() {
        AllEvents_Appointment.Print();
    }

}
