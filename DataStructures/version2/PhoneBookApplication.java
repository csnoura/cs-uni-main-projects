/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package phonebookapplication;

import java.util.Scanner;

/**
 *
 * @author noura
 */
public class PhoneBookApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        phoneBook phonebook1 = new phoneBook("USER");
        Scanner input = new Scanner(System.in);
        System.out.println("Welcome to the Linked/Tree Phonebook!");
        int pick;
        System.out.println("Press enter for the menue!");
        //loop for user menue
        do {
            input.nextLine();
            System.out.println("Please choose an option:");
            System.out.println("1.Add a contact\n2.Search for a contact\n3.Delete a contact\n4.Schedule an event\n5.Print event details\n6.Print contacts by firstname\n7.Print all events alphabetically \n8.Print all Contacts\n9.Exit\n");
            System.out.println("Enter your choice:");
            pick = input.nextInt();
            input.nextLine();
            //switch to display the cases
            switch (pick) {
                //add contact
                case 1:
                    String name,
                     phonenumber,
                     email,
                     address,
                     birthday,
                     notes;
                    System.out.println("Enter the contact's name:");
                    name = input.nextLine();
                    //input.nextLine();
                    System.out.println("Enter the contact's phonenumber :");
                    phonenumber = input.nextLine();
                    System.out.println("Enter the contact's email: ");
                    email = input.nextLine();
                    System.out.println("Enter the contact's address: ");
                    address = input.nextLine();
                    System.out.println("Enter the contact's birthday:");
                    birthday = input.nextLine();
                    System.out.println("Enter the contact's notes:");
                    notes = input.nextLine();
                    contact c1 = new contact(name, phonenumber, email, address, birthday, notes);
                    phonebook1.addContact(c1);
                    break;
                //search for a contact
                case 2:

                    System.out.println("Enter search criteria: ");
                    System.out.print(" 1. Name\n" + " 2. Phone Number\n" + " 3. Email Address\n" + " 4. Address\n" + " 5. Birthday\n");
                    System.out.println("Enter your choice: ");
                    int choice = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter the contact's Inf ");
                    String ContactAttr = input.nextLine();
                    phonebook1.SearchContact(ContactAttr, choice);

                    break;
                //delete an event
                case 3:

                    System.out.println("Enter Name to delete Contact : ");
                    String DeleteCbyN = input.nextLine();
                    phonebook1.RemoveConatct(DeleteCbyN);

                    break;
                //schedule an event 
                case 4:

                    String title,
                     ConName,
                     date,
                     time,
                     location;
                    int type;
                    System.out.println("Enter \n" + "1. Appointment \n" + "2. Event");
                    int pick2 = input.nextInt();
                    input.nextLine();
                    if (pick2 == 2) {
                        type = 2;

                    } else {
                        type = 1;
                    }
                    System.out.println("Enter Event/Appointment title:");
                    title = input.nextLine();
                    System.out.println("Enter contact name:");
                    ConName = input.nextLine();
                    System.out.print("Enter event time(HH:MM):");
                    time = input.next();//+

                    System.out.print("Enter event date(MM/DD/YYYY):");
                    date = input.next();//+
                    System.out.println("Enter event location:");
                    location = input.next();
                    input.nextLine();
                    event e1 = new event(type, title, time, date, location);
                    phonebook1.ScheduleEvent(e1, ConName);
                    if (type == 2) {
                        System.out.println("How extra many contact do you want to add in this event :"); // many conatct with many events
                        int numOfCon = input.nextInt();
                        input.nextLine();

                        for (int i = 1; i <= numOfCon; i++) { // loop for adding extra contacts to an event
                            System.out.println("Enter contact name:");
                            ConName = input.nextLine();

                            phonebook1.ScheduleEvent(e1, ConName);

                        }
                    }
                    break;
                //print event details
                case 5:
                    int ch;
                    System.out.println("Enter search criteria:\n 1.Contatc Name.\n 2.Event Title");
                    ch = input.nextInt();
                    input.nextLine();
                    if (ch == 1) {
                        System.out.println("Enter contact name:");
                        String CN = input.nextLine();
                        phonebook1.SearchContact(CN, 7);
                    } else {
                        System.out.println("Enter the event title:");
                        String t = input.nextLine();
                        phonebook1.PrintByTitle(t);
                    }
                    break;
                //print contact by first name    
                case 6:
                    System.out.println("Enter the first name:");
                    String fname = input.next();
                    input.nextLine();
                    phonebook1.SearchContact(fname, 6);
                    System.out.println();
                    break;
                //print all events alphabetically
                case 7:
                    phonebook1.PrintAllEvents();
                    break;
                //print all contact
                case 8:
                    System.out.println("Print all contacts in the phonebook:");
                    phonebook1.Printallcontact();
                    break;
            }

        } while (pick != 9);
    }

}
