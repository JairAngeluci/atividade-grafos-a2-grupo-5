/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

/**
 *
 * @author leona
 */
public class Node {
    private String name;
    private Node next;

    public Node(String name) {
        this.name = name;
        next = null;
    }

    public Node getNext() {
        return this.next;
    }

    public String getName() {
        return this.name;
    }

    public void addNext(Node novoNo) {
        this.next = novoNo;
    }
}
