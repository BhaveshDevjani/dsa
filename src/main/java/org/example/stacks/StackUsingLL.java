package org.example.stacks;

public class StackUsingLL {
    private int top;
    private Node head;

    public StackUsingLL() {
        top = -1;
        head = null;
    }

    public int getSize() {
        return top +1;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void push(int element) {
        Node node = new Node(element);
        node.next = head;
        head = node;
        top++;
    }

    public int pop() {
        if (head == null) {
            return -1;
        }
        int element = head.data;
        head = head.next;
        top--;
        return element;
    }

    public int top() {
        if (head == null) {
            return -1;
        }
        return head.data;
    }
}


class Node {
    int data;
    Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }

}
