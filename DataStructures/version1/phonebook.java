/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phonebook;

/**
 *
 * @author noura
 */
public class phonebook {

    private String name;
    private LinkedList<Contact> Contacts;
    private LinkedList<Event> allEvents;
    //allEvents contains all event in a phonebook

    public phonebook(String name) {
        this.name = name;
        Contacts = new LinkedList<Contact>();
        allEvents = new LinkedList<Event>();
    }
//this method search for contacts by a particular criteria that is represented by a number 
//taken by the user called "pick", and add the contacts that share the same value in 
// a new list then it returns the new list

    public LinkedList<Contact> searchContact(String data, int pick) {

        //create a new list 
        LinkedList<Contact> TempList = new LinkedList<Contact>();
        // put the current at the first contact
        Contacts.FindFirst();
        //create a criteriaValue varible to assign the criteria value of the current contact
        String criteriaValue = "";
        //switch in loop to search through the Contacts List by the criteria
        if (!Contacts.empty()) {
            while (!Contacts.Last()) {

                switch (pick) {
                    //take the current contact name

                    case 1:
                        criteriaValue = Contacts.Retrieve().getName();
                        break;

                    case 2:
                        //take the current contact phone number

                        criteriaValue = Contacts.Retrieve().getPhoneNumber();
                        break;

                    case 3:
                        //take the current contact email

                        criteriaValue = Contacts.Retrieve().getEmail();
                        break;

                    case 4:
                        //take the current contact address

                        criteriaValue = Contacts.Retrieve().getAddress();
                        break;

                    case 5:
                        //take the current contact birthday

                        criteriaValue = Contacts.Retrieve().getBirthday();
                        break;

                    case 6:
                        //take the current contact first name

                        criteriaValue = Contacts.Retrieve().getFirstName();
                        break;

                }
                //check if the current value equal to the criteriaValue
                if (criteriaValue.equalsIgnoreCase(data)) {
                    //add the current data at the new list 
                    TempList.add(Contacts.Retrieve());
                    //check if the added contact in TempList is searched by the name or phonenumber
                    if (pick == 1 || pick == 2) {
                        //return the new list since the name and phone number for a contact is unique
                        return TempList;
                    }
                }
                // move to the next contact
                Contacts.FindNext();

            }
            //duplicate code for the last contact
            switch (pick) {
                //take the current contact name

                case 1:
                    criteriaValue = Contacts.Retrieve().getName();
                    break;

                case 2:
                    //take the current contact phone number

                    criteriaValue = Contacts.Retrieve().getPhoneNumber();
                    break;

                case 3:
                    //take the current contact email

                    criteriaValue = Contacts.Retrieve().getEmail();
                    break;

                case 4:
                    //take the current contact address

                    criteriaValue = Contacts.Retrieve().getAddress();
                    break;

                case 5:
                    //take the current contact birthday

                    criteriaValue = Contacts.Retrieve().getBirthday();
                    break;

                case 6:
                    //take the current contact first name

                    criteriaValue = Contacts.Retrieve().getFirstName();
                    break;

            }
            //check if the current value equal to the criteriaValue
            if (criteriaValue.equalsIgnoreCase(data)) {
                //add the current data at the new list 
                TempList.add(Contacts.Retrieve());
                //check if the added contact in TempList is searched by the name or phonenumber
                if (pick == 1 || pick == 2) {
                    //return the new list since the name and phone number for a contact is unique
                    return TempList;
                }
                //return the new list TempList
            }

        }
        //return the new list TempList
        return TempList;
    }
// methode that add contact by name:
//1- if the list is empty then it will insert it without any condition
//2-if the new contact is smaller(high priority for example: A has a high priority then b)
//then the new contact inserted before the current contact.
//3-if the new contact is greater (low priority for example: Z has a low priority then A)
//then the new contact inserted after the current contact.

    public void addContact(Contact NewContact) {
        // Create a temp contact variable.
        Contact temp;
        // Check if the contact info is valid.
        if (CheckValidation(NewContact.getName(), NewContact.getPhoneNumber())) {
            // Move current to the first contact in the list.
            Contacts.FindFirst();
            // Check if the list is empty.
            if (Contacts.empty()) {
                // Add the new contact to the list.
                Contacts.add(NewContact);

            } else {
                //loop until we reach the last contact
                while (!Contacts.Last()) {
                    // Check if the new contact name is smaller than the current contact name in the list 
                    //the new contact should be inserted before the current contact.
                    if (Contacts.Retrieve().compareTo(NewContact) > 0) {
                        // Store the first contact name in the temp variable.
                        temp = Contacts.Retrieve();
                        // Update the the current node in the Contacts list with the new contact(data).
                        Contacts.Update(NewContact);
                        // Store the first contact name in the temp variable.
                        Contacts.add(temp);
                        //break from the loop if we found the right place
                        break;
                    }
                    //move to the next contact

                    Contacts.FindNext();
                }
                // repeated code to check the last contact if we reach it 

                if (Contacts.Last()) {
                    if (Contacts.Retrieve().compareTo(NewContact) > 0) {
                        temp = Contacts.Retrieve();
                        Contacts.Update(NewContact);
                        Contacts.add(temp);

                    } else {
                        //this for if the new contact is greater than the last name of the last contact by ascii code

                        Contacts.add(NewContact);
                    }
                }

            }
            System.out.println("A contact is added successfully");
        } else {
            System.out.println("Threre is A contact with same name or the same Phone Number");
        }

    }
// method to print the received list, it will mostly print
// a list from searchContact method 

    public void PrintBy(LinkedList<Contact> contacts) {
        //check if empty
        if (!contacts.empty()) {
            //move to the first contact
            contacts.FindFirst();
            // loop until the last contact
            while (!contacts.Last()) {
                //printing the current contact from the received list
                System.out.println(contacts.Retrieve().toString());
                //move to the next contact
                contacts.FindNext();
            }
            //printing the last contact
            System.out.println(contacts.Retrieve().toString());
        } else {
            //message if the list is empty
            System.out.println("The List is empty!");
        }
    }
//method to print Contacts list 

    public void PrintAllContacts() {
        Contacts.Print();
    }
// method to print allEvents list and it will be printed ordered alphabetically
// since allEvents is orderd

    public void PrintAllEvents() {
        allEvents.Print();
    }
//method to check if the received name and phone number is unique or not
//it will return true if it unique otherwise it will return false

    public boolean CheckValidation(String name, String phoneNumber) {
        //check if the list is empty
        if (!Contacts.empty()) {
            //move to the first contact
            Contacts.FindFirst();
            //loop search through the Contacts list 
            while (!Contacts.Last()) {
                //if the name or phone number is found in the current contact then it will return false
                if (Contacts.Retrieve().getName().equalsIgnoreCase(name) || Contacts.Retrieve().getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                    return false;
                } else {
                    //move to the next contact
                    Contacts.FindNext();
                }
            }
            //if the name or phone number is found in the last contact then it will return false
            if (Contacts.Retrieve().getName().equalsIgnoreCase(name) || Contacts.Retrieve().getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                return false;
            }
        }
        //return true if the name and the phone number is unique
        return true;
    }
//method to remove contact by name or phonenumber from the phonebook and remove the events associated with it 
// *note that* if an event is associated with more than one contact 
//then it will not be removed from the allEvents list

    public void RemoveContact(String key, int pick) {
        //check if the Contacts list is empty or not
        if (!Contacts.empty()) {
            //set current to first contact
            Contacts.FindFirst();
            //check if the contact exists in the Contacts list
            //false = exists
            //true = does not exsits
            if (((pick == 1 && CheckName(key)) || (pick == 2 && CheckPhoneNumber(key))) == true) {

                System.out.println("The phone number or the name you have provieded does not exist");
                return;

            } else {

                //switch in loop to search for the choosen contact 
                //switch needed so we can remove by name or phonenumber
                while (!Contacts.Last()) {
                    switch (pick) {
                        //case removing name
                        case 1:
                            //if the given name equals the current contact name
                            if (Contacts.Retrieve().getName().equalsIgnoreCase(key)) {
                                if (Contacts.Retrieve().getEvents() != null) //remove the associated events
                                {
                                    RemoveEvents(Contacts.Retrieve());
                                }
                                //remove the contact
                                Contacts.Remove();
                                System.out.println("Contact removed successfully!");

                                return;

                            }
                            break;
                        //case removing by phone number 
                        case 2:
                            //if the given phone number equals the current contact phone number

                            if (Contacts.Retrieve().getPhoneNumber().equalsIgnoreCase(key)) {
                                //remove the associated events

                                RemoveEvents(Contacts.Retrieve());

                                //remove the contact
                                Contacts.Remove();
                                System.out.println("Contact removed successfully!");
                                return;
                                //assign it to true since phone number is unique

                            }
                            break;
                    }

                    //move to the next contact
                    Contacts.FindNext();

                }
                // duplicate code for the last contact
                switch (pick) {
                    //case removing name
                    case 1:
                        //if the given name equals the current contact name
                        if (Contacts.Retrieve().getName().equalsIgnoreCase(key)) {
                            //remove the associated events

                            RemoveEvents(Contacts.Retrieve());

                            //remove the contact
                            Contacts.Remove();
                            System.out.println("Contact removed successfully!");
                        }
                        break;
                    //case removing by phone number 
                    case 2:
                        //if the given phone number equals the current contact phone number

                        if (Contacts.Retrieve().getPhoneNumber().equalsIgnoreCase(key)) {
                            if (Contacts.Retrieve().getEvents() != null) //remove the associated events
                            {
                                RemoveEvents(Contacts.Retrieve());
                            }
                            //remove the contact
                            Contacts.Remove();
                            System.out.println("Contact removed successfully!");
                            //assign it to true since phone number is unique

                        }
                        break;
                }

            }
            System.out.println("Contact removed successfully!");

        } else {
            System.out.println("The contacts list is empty");
        }

    }
//this method adds event in the allEvent liste (ordered alphabetically) :
////1- if the list is empty then it will insert it without any condition
//2-if the new event is smaller(high priority for example: A has a high priority then b)
//then the new event inserted before the current event.
//3-if the new event is greater (low priority for example: Z has a low priority then A)
//then the new event inserted after the current event.

    public void addEvents(Event NewEvent) {
        // Create a temp Event variable.
        Event temp;

        // Move current to the first event in the list.
        allEvents.FindFirst();
        // Check if the list is empty.
        if (allEvents.empty()) {
            // Add the new event to the list.
            allEvents.add(NewEvent);

        } else {
            //loop until we reach the last event
            while (!allEvents.Last()) {
                // Check if the new event title is smaller than the current event title in the list 
                //the new event should be inserted before the current event.
                if (allEvents.Retrieve().compareTo(NewEvent) > 0) {
                    // Store the first event title in the temp variable.
                    temp = allEvents.Retrieve();
                    // Update the the current node in the allEvents list with the new event(data).
                    allEvents.Update(NewEvent);
                    // Store the first event name in the temp variable.
                    allEvents.add(temp);
                    //break from the loop if we found the right place
                    break;
                }
                //move to the next event

                allEvents.FindNext();
            }
            // repeated code to check the last event if we reach it 

            if (allEvents.Last()) {
                if (allEvents.Retrieve().compareTo(NewEvent) > 0) {
                    temp = allEvents.Retrieve();
                    allEvents.Update(NewEvent);
                    allEvents.add(temp);

                } else //this for if the new event title is greater than the last title  of the last contact by ascii code
                {
                    allEvents.add(NewEvent);
                }

            }

        }
        System.out.println("An event is added successfully");
    }

//this method Schedule Event for a contact that Exists in Contacts list
//the method handles cases which are 
//1- if the event is new , 2- if the event already Exists in the phonebook
//3- if the contact does not Exists ,4- if there is conflict in time
    public void ScheduleEvent(Event newEvent, String contactName) {
        //boolean varible to show if a there is a conflict or not
        boolean conflictEsists = false;
        //get the contact object 
        Contact contact = GetContactName(contactName);
        //check if the contact does Exists
        if (CheckName(contactName) == false) {

            //set current to the first event
            allEvents.FindFirst();
            //check if the list is not empty 
            if (!allEvents.empty()) {
                //loop until the last event
                while (!allEvents.Last()) {
                    //check if the time and date exists in allEvents list

                    if (allEvents.Retrieve().getTime().equals(newEvent.getTime()) && allEvents.Retrieve().getDate().equals(newEvent.getDate())) {
                        if (!allEvents.Retrieve().getTitle().equals(newEvent.getTitle())) //means there is a conflict, since the event dose not Exists in the allEvents list
                        {
                            conflictEsists = true;
                        } else {
                            //add the contact in the contacts list attribute of newEvent object
                            newEvent.getContacts().add(contact);
                            //add the newEvent in the Events list attribute of the contact object
                            contact.getEvents().add(newEvent);
                            System.out.println("A new event is added!");
                            //close the methode since the event already exists and found
                            return;
                        }
                    } else //move to the next event
                    {
                        allEvents.FindNext();
                    }
                }
                //check if the time and date exists in the last event in the list

                if (allEvents.Retrieve().getTime().equals(newEvent.getTime()) && allEvents.Retrieve().getDate().equals(newEvent.getDate())) {
                    if (!allEvents.Retrieve().getTitle().equals(newEvent.getTitle())) //means there is a conflict since the event dose not Exists in the allEvents list
                    {
                        conflictEsists = true;
                    } else {
                        //add the contact in the Contacts list attribute of newEvent object
                        contact.getEvents().add(newEvent);
                        //add the newEvent in the Events list attribute of the contact object
                        newEvent.getContacts().add(contact);
                        System.out.println("A new event is added!");
                        //close the methode since the event already exists and found
                        return;
                    }
                }
                //check if there's a conflict 
                if (conflictEsists == true) {
                    System.out.println("there's a conflict in Time");
                    //close the methode so we dont add the newEvent in the list
                    return;
                }
            }
            //add the newEvent in the allEvents list 
            addEvents(newEvent);
            //add the contact in the Contacts list attribute of newEvent object
            newEvent.getContacts().add(contact);
            //add the newEvent in the Events list attribute of the contact object
            contact.getEvents().add(newEvent);
            System.out.println("A new event is added!");

        } else {
            System.out.println("contact does not Exists");
        }
    }

    public void PrintByTitle(String title) {
        //set the current to the fisrt event
        allEvents.FindFirst();
        //check if the list is empty or not 
        if (!allEvents.empty()) {
            //loop to search in allEvents 
            while (!allEvents.Last()) {
                //check if the current event share the same title 
                if (allEvents.Retrieve().getTitle().equals(title)) {
                    //print the current event that share the same title 
                    allEvents.Retrieve().PrintWithContact();
                }
                //move to next event
                allEvents.FindNext();
            }
            //check if the last event share the same title 
            if (allEvents.Retrieve().getTitle().equals(title)) {
                //print the last event that share the same title 
                allEvents.Retrieve().PrintWithContact();
            }
        } else {
            System.out.println("the event list is empty");
        }
    }
//thia method returns a contact object by searching it by it name in the Contacts list

    public Contact GetContactName(String name) {
        //check if the list is empty or not 
        if (!Contacts.empty()) {
            //set the current to the fisrt event
            Contacts.FindFirst();
            //loop to search in Contacts 
            while (!Contacts.Last()) {
                //check if the current contact name equal to the given name
                if (Contacts.Retrieve().getName().equalsIgnoreCase(name)) {
                    //retrun the current contact 
                    return Contacts.Retrieve();
                }
                Contacts.FindNext();
            }
            //check if the last contact name equal to the given name
            if (Contacts.Retrieve().getName().equalsIgnoreCase(name)) {
                //retrun the current contact 
                return Contacts.Retrieve();
            }
        } else {
            System.out.println("The List is empty");
        }
        //retrun null if the list is empty or the contact is not found 
        return null;
    }
//this method for removing events from the allEvent list by a contact object
// simply if a contact is removed we need to remove the events associated with it 
// *note that* if an event is associated with more than one contact 
    //then it will not be removed from the allEvents list

    public void RemoveEvents(Contact c) {
        //get the contact events and store it in a linked list object 
        LinkedList<Event> cEvents = c.getEvents();
        //set current to the first event in the contact c events
        cEvents.FindFirst();

        //check if the list is empty or not
        if (!cEvents.empty() && !allEvents.empty()) {
            //loop to search in contact c events
            while (!cEvents.Last()) {
                //check if the current event in the contact c events is of 
                //length 1(true), that means it associated only with c contact object,
                //so if it true then we will search for it in the allEvents list so it can be removed
                if (cEvents.Retrieve().getContacts().Length() == true && allEvents.Search(cEvents.Retrieve()) == true) {
                    //remove the event in the contact c events that is only associated with c contact object
                    allEvents.Remove();

                }
                //move to the next event
                cEvents.FindNext();

            }
            //check if the last event in the contact c events is of 
            //length 1(true), that means it associated only with c contact object,
            //so if it true then we will search for it in the allEvents list so it can be removed
            if (cEvents.Retrieve().getContacts().Length() == true && allEvents.Search(cEvents.Retrieve())) {
                //remove the last event in the contact c events that is only associated with c contact object
                allEvents.Remove();
            }
        } else {
            System.out.println("the contact has no events to remove or there's no events in this phonebook");
        }
    }

    public boolean CheckName(String name) {
        //check if the list is empty
        if (!Contacts.empty()) {
            //move to the first contact
            Contacts.FindFirst();
            //loop search through the Contacts list 
            while (!Contacts.Last()) {
                //if the name is found in the current contact then it will return false
                if (Contacts.Retrieve().getName().equalsIgnoreCase(name)) {
                    return false;
                } else {
                    //move to the next contact
                    Contacts.FindNext();
                }
            }
            //if the name is found in the last contact then it will return false
            if (Contacts.Retrieve().getName().equalsIgnoreCase(name)) {
                return false;
            }
        }
        return true;
    }

    public boolean CheckPhoneNumber(String phoneNumber) {
        //check if the list is empty
        if (!Contacts.empty()) {
            //move to the first contact
            Contacts.FindFirst();
            //loop search through the Contacts list 
            while (!Contacts.Last()) {
                //if  phone number is found in the current contact then it will return false
                if (Contacts.Retrieve().getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                    return false;
                } else {
                    //move to the next contact
                    Contacts.FindNext();
                }
            }
            //if phone number is found in the last contact then it will return false
            if (Contacts.Retrieve().getPhoneNumber().equalsIgnoreCase(phoneNumber)) {
                return false;
            }

        }
        return true;
    }
}
