package org.example.queues;

public class QueueUsingLL {

    private Node front;
    private Node rear;

    private int size;
    public QueueUsingLL() {
        front = null;
        rear = null;
        size = 0;
    }
    public int getSize() {
        return size;
    }


    public boolean isEmpty() {
        return getSize()==0;
    }


    public void enqueue(int data) {
        Node node = new Node(data);
        if(front==null) {
            front = node;
        } else {
            rear.next = node;
        }
        rear = node;
        size++;
    }


    public int dequeue() {
        if (front == null) {
            return -1;
        }
        int temp = front.data;
        front = front.next;
        size--;
        if (getSize()==0) {
            rear = null;
        }
        return temp;
    }


    public int front() {
        if (front == null) {
            return -1;
        }
        return front.data;
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
