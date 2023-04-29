package org.example.queues;

public class QueueUsingArray {

    private int[] data;
    private int front;
    private int rear;
    private int size;

    public QueueUsingArray() {
        data = new int[10];
        front = -1;
        rear = -1;
        size = 0;
    }

    public QueueUsingArray(int capacity) {
        data = new int[capacity];
        front = -1;
        rear = -1;
        size = 0;
    }

    public boolean isEmpty() {
        return getSize()==0;
    }

    public int getSize(){
        return size;
    }

    public int getFront() {
        if (getSize() == 0) {
            throw new RuntimeException("Queue is Empty, so front not available");
        }
        return data[front];
    }

    public void enqueue(int element) {
        if (getSize()== data.length) {
            System.out.println("Resizing needed!");
            resize();
        }
        if (getSize()==0) {
            front++;
        }
        rear = (rear+1)% data.length;
        size++;
        data[rear] = element;
    }

    public int dequeue() {
        if (getSize() == 0) {
            throw new RuntimeException("Queue is Empty, so cannot dequeue");
        }
        int temp = data[front];
        front = (front+1)% data.length;
        size--;
        if (getSize() == 0) {
            front = -1;
            rear = -1;
        }
        return temp;
    }

    private void resize() {
        int[] newData = new int[data.length*2];
        int temp = front;
        int i=0;
        while (i<getSize()) {
            newData[i++] = data[temp];
            temp = (temp+1)% data.length;
        }
        front = 0;
        rear = getSize()-1;
        data = newData;
    }

}
