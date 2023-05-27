package org.example.priorityqueues;

import java.util.ArrayList;

public class MinHeap<T extends Comparable<T>> implements PriorityQueue<T> {
    ArrayList<T> heap;

    public MinHeap() {
        heap = new ArrayList<T>();
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public T getMin() {
        if (isEmpty()) return null;
        return heap.get(0);
    }

    @Override
    public void insert(T data) {
        heap.add(data);
        upHeapify();
    }

    private void upHeapify() {
        int index = heap.size() - 1;
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parent)) < 0) {
                swap(index, parent);
                index = parent;
            } else break;
        }
    }

    private void swap(int index, int parent) {
        T temp = heap.get(parent);
        heap.set(parent, heap.get(index));
        heap.set(index, temp);
    }

    @Override
    public T delete() {
        T data = heap.get(0);
        heap.set(0, heap.get(size() - 1));
        heap.remove(size() - 1);
        downHeapify();
        return data;
    }

    private void downHeapify() {
        int index = 0;
        int leftIndex = 1;
        int rightIndex = 2;
        while (leftIndex < size()) {
            if ((rightIndex == size() && heap.get(index).compareTo(heap.get(leftIndex)) > 0) || (heap.get(index).compareTo(heap.get(leftIndex)) > 0 && heap.get(rightIndex).compareTo(heap.get(leftIndex)) > 0)) {
                swap(index, leftIndex);
                index = leftIndex;
            } else if (rightIndex < heap.size() && heap.get(index).compareTo(heap.get(rightIndex)) > 0 && heap.get(leftIndex).compareTo(heap.get(rightIndex)) > 0) {
                swap(index, rightIndex);
                index = rightIndex;
            } else break;
            leftIndex = 2 * index + 1;
            rightIndex = 2 * index + 2;
        }
    }
}
