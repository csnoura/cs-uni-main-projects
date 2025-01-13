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
 */
/**
 *
 * @author noura
 * @param <T>
 */
public class Node<T> {

    private T Data;
    private Node<T> Next;

    public Node(T data) {
        Data = data;
        Next = null;
    }

    public T getData() {
        return Data;
    }

    public void setData(T Data) {
        this.Data = Data;
    }

    public Node<T> getNext() {
        return Next;
    }

    public void setNext(Node<T> Next) {
        this.Next = Next;
    }

}
