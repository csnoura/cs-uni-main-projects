/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phonebookapplication;

/**
 *
 * @author noura
 * @param <T>
 */
public class Linkedlist<T> {

    /**
     *
     * @author noura
     * @param <T>
     */
    private Node<T> current;
    private Node<T> head;

    public Linkedlist() {
        head = current = null;
    }

    public void add(T data) {
        Node<T> temp;
        if (empty()) {
            head = current = new Node<T>(data);
        } else {
            temp = current.getNext();
            current.setNext(new Node<T>(data));
            current = current.getNext();
            current.setNext(temp);
        }
    }

    public boolean Search(T key) {
        if (!empty()) {
            Node<T> tmp = current;
            current = head;
            while (current != null) {
                if (current.getData().equals(key)) {
                    return true;
                }
                current = current.getNext();
            }
            current = tmp;
        }
        return false;
    }

    public boolean empty() {
        return head == null;
    }

    public Node<T> getCurrent() {
        return current;
    }

    public void setCurrent(Node<T> current) {
        this.current = current;
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public void FindFirst() {
        current = head;
    }

    public boolean Last() {
        return current.getNext() == null;
    }

    public void FindNext() {
        current = current.getNext();
    }

    public T Retrieve() {
        return current.getData();
    }

    public void Update(T data) {
        current.setData(data);
    }

    public void Remove() {
        if (current == head) {
            head = head.getNext();
        } else {
            Node<T> tmp = head;
            while (tmp.getNext() != current) {
                tmp = tmp.getNext();
            }
            tmp.setNext(current.getNext());
        }
        if (current.getNext() == null) {
            current = head;
        } else {
            current = current.getNext();
        }
    }

    public void Print() {
        Node<T> temp = head;
        if (!empty()) {
            while (temp != null) {
                System.out.println(temp.getData().toString() + "\n");
                temp = temp.getNext();
            }
        } else {
            System.out.println("The Events in the phonebook is empty");
        }
    }

}
