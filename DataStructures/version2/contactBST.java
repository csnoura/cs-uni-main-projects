/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phonebookapplication;

/**
 *
 * @author noura
 */
public class contactBST {

    BTNode<contact> root, current;

    public contactBST() {
        root = current = null;
    }

    public boolean empty() {
        return root == null;
    }

    public contact Retrieve() {
        return current.getData();
    }

    public void update(contact val) {
        current.setData(val);
    }

    public boolean findkey(String name) {
        BTNode<contact> p = root, q = root;
        if (empty()) {
            return false;
        }
        while (p != null) {
            q = p;
            if (p.getKey().compareTo(name) == 0) {
                current = p;
                return true;
            } else if (p.getKey().compareTo(name) > 0) {
                p = p.getLeft();
            } else {
                p = p.getRight();
            }
        }
        current = q;
        return false;
    }
// methode that add contact by name as a key, we have 3 cases :
//1- if the list is empty then it will insert it without any condition
//2-if the new contact is bigger then its on the right of the current node
//3-if the new contact is smaller then its on the left of the curren node

    public boolean insert(contact c) {
        BTNode<contact> p, q = current;
//making sure the key is unique
        if (findkey(c.getName())) {
            current = q; //put current on it correct place
            return false; // key already in the BST
        }
        p = new BTNode<contact>(c.getName(), c);
//for case 1:
        if (empty()) {
            root = current = p;
            return true;
        }
//flag1 is to help us call method Searchcontact when we check  
//if the phone number is unique or not but 
//without printing the info of the contact who has the phone number by assign it with false
//----------
//flag 2 is to help us to know if method Searchcontact 
//found the phone number of the new contact or not 
//true = found , false=unique
        BooleanWrapper flag = new BooleanWrapper(false);
        BooleanWrapper flag2 = new BooleanWrapper(false);
//calling the method to check if the phone number of the new contact is unique or not
        Searchcontact(root, 2, c.getPhoneNumber(), flag, flag2);
        if (flag2.equals(true)) {
            return false;
        } else {
            if (current.getData().compareTo(c) > 0) {
                //// for case 3:
                current.setLeft(p);
            } else //for case2:
            {
                current.setRight(p);
            }
            current = p;
            return true;
        }
    }

//method to call Searchcontact
    public boolean pSearchContact(String data, int pick) {
        //flag1 is to help us to print contacts who has the same data
        //flag 2 is to help us to know if method Searchcontact 
        //found contact with the given data or not
        //true = found , false=not found
        BooleanWrapper flag = new BooleanWrapper(true);
        BooleanWrapper flag2 = new BooleanWrapper(false);
        Searchcontact(root, pick, data, flag, flag2);
        if (flag2.equals(true)) {
            return true;
        } else {
            return false;
        }
    }

//method to call pprint
    public void pprint(int pick) {
        print(root, pick);

    }
//method to print the contact bst by in-order 
//pick =1 ,then will print all the info of the contact
//pick =2, then will print only the name of the contact

    private void print(BTNode<contact> pointer, int pick) {
        if (pointer == null) {
            return;
        }
        print(pointer.getLeft(), pick);
        if (pick == 1) {
            System.out.println(pointer.getData() + "\n");
        }
        if (pick == 2) {
            System.out.println("Contact name: " + pointer.getData().getName());
        }
        print(pointer.getRight(), pick);

    }
//method to search for contacts who share the same data by in-order
//pick is to choose criteria
//if flag = true, then all the contacts info who share the same data will be printed
//if flag=false,then nothing will be printed
//if flag2=true ,then match is found 
// if falg2=false then no match is found 

    private void Searchcontact(BTNode<contact> p, int pick, String data, BooleanWrapper flag, BooleanWrapper flag2) {
        //string to store the data from the switch
        String criteriaValue = "";
        if (p == null) {
            return;
        }
        Searchcontact(p.getLeft(), pick, data, flag, flag2);
        switch (pick) {
            //take the p contact name

            case 1:
                criteriaValue = p.getData().getName();
                break;

            case 2:
                //take the p contact phone number

                criteriaValue = p.getData().getPhoneNumber();
                break;

            case 3:
                //take the p contact email

                criteriaValue = p.getData().getEmail();
                break;

            case 4:
                //take the p contact address

                criteriaValue = p.getData().getAddress();
                break;

            case 5:
                //take the p contact birthday

                criteriaValue = p.getData().getBirthday();
                break;

            case 6:
                //take the p contact first name

                criteriaValue = p.getData().getFirstName();
                break;
            case 7:
                //take p contact name to print its event only
                criteriaValue = p.getData().getName();
                break;
        }
        //check if the data  equal to the criteriaValue
        if (criteriaValue.equalsIgnoreCase(data)) {
            //flag2 set to true which means a match is found 
            flag2.set(true);
            //case 7 is only to print the events of a contact only
            if (pick == 7) {
                p.getData().getEvents().Print();
                return;
            }
            //flag set to true which means we want to print the info of the contact we have found 
            if (flag.get() == true) {
                System.out.println(p.getData().toString() + "\n");
            }
            //line A
            //since the name and phone number unique we can stop search if we found the contact
            if (pick == 1 || pick == 2 || pick == 7) {
                return;
            }

        }

        Searchcontact(p.getRight(), pick, data, flag, flag2);

    }

    public BooleanWrapper remove_key(String tkey) {
        BooleanWrapper removed = new BooleanWrapper(false);
        BTNode<contact> p;
        p = remove_aux(tkey, root, removed);
        current = root = p;
        return removed;
    }

    //find min in bst
    private BTNode<contact> find_min(BTNode<contact> p) {
        if (p == null) {
            return null;
        }
        while (p.getLeft() != null) {
            p = p.getLeft();
        }
        return p;
    }
    //remove node from bst by key

    private BTNode<contact> remove_aux(String key, BTNode<contact> p, BooleanWrapper flag) {
        BTNode<contact> q, child = null;
        if (p == null) {
            return null;
        }
        if (p.getKey().compareTo(key) > 0) {
            p.setLeft(remove_aux(key, p.getLeft(), flag));
        } else if (p.getKey().compareTo(key) < 0) {
            p.setRight(remove_aux(key, p.getRight(), flag));
        } else { // key is found
            flag.set(true);
            if (p.getLeft() != null && p.getRight() != null) { //two children
                q = find_min(p.getRight());
                p.setKey(q.getKey());
                p.setData(q.getData());
                p.setRight(remove_aux(q.getKey(), p.getRight(), flag));
            } else {
                if (p.getRight() == null) //one child
                {
                    child = p.getLeft();
                } else if (p.getLeft() == null) //one child
                {
                    child = p.getRight();
                }
                return child;
            }
        }
        return p;
    }
}
