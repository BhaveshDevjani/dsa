package org.example.priorityqueues;

public interface PriorityQueue<T> {

    int size();
    boolean isEmpty();
    default T getMin() {
        return null;
    }
    default T getMax() {
        return null;
    }
    void insert(T data);
    T delete();

}
