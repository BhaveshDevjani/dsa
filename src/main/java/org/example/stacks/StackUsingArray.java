package org.example.stacks;

import java.util.ArrayList;
import java.util.List;

public class StackUsingArray {

    private int[] data;
    private int top;

    public StackUsingArray() {
        data = new int[10];
        top = -1;
    }

    public StackUsingArray(int capacity) {
        data = new int[capacity];
        top = -1;
    }

    public int size() {
        return top +1;
    }

    public boolean isEmpty() {
        return size()==0;
    }

    public int top() throws RuntimeException {
        if(size() == 0) {
            throw new RuntimeException("Size of Stack is Empty");
        }
        return data[top];
    }

    public void push(int data) {
        if (size() == this.data.length) {
            System.out.println("Resizing Needed!");
            resize();
        }
        this.data[++top] = data;
    }

    public int pop() throws RuntimeException {
        if(size() == 0) {
            throw new RuntimeException("Stack is Empty so cannot pop");
        }
        int data = this.data[top];
        top--;
        return data;
    }

    private void resize() {
        int[] newData = new int[data.length*2];
        for(int i=0; i<data.length; i++) {
            newData[i] = data[i];
        }
        this.data = newData;
    }
}
