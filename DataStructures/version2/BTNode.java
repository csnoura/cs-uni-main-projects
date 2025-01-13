/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package phonebookapplication;

/**
 *
 * @author noura
 */
public class BTNode<T> {

    private String key;
    private T data;
    private BTNode<T> left, right;

    public BTNode(String k, T val) {
        key = k;
        data = val;
        left = right = null;
    }

    public BTNode(String k, T val, BTNode<T> l, BTNode<T> r) {
        key = k;
        data = val;
        left = l;
        right = r;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BTNode<T> getLeft() {
        return left;
    }

    public void setLeft(BTNode<T> left) {
        this.left = left;
    }

    public BTNode<T> getRight() {
        return right;
    }

    public void setRight(BTNode<T> right) {
        this.right = right;
    }

}
