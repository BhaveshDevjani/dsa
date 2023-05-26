package org.example.hashmaps;

import java.util.ArrayList;

public class CustomHashMap<K, V> implements CustomMap<K, V> {
    private ArrayList<HashMapNode<K, V>> bucketArray;
    private int bucketSize;
    private int size;
    private static final double LOAD_FACTOR_THRESHOLD = 0.7;

    public CustomHashMap() {
        this.bucketSize = 20;
        this.size = 0;
        initBucket();
    }

    public CustomHashMap(int bucketSize) {
        this.bucketSize = bucketSize;
        this.size = 0;
        initBucket();
    }

    private void initBucket() {
        bucketArray = new ArrayList<>();
        for (int i = 0; i < bucketSize; i++) {
            bucketArray.add(null);
        }
    }

    @Override
    public void insert(K key, V value) {
        int bucketIndex = getBucketIndex(key);
        HashMapNode<K, V> head = bucketArray.get(bucketIndex);
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
        size++;
        head = bucketArray.get(bucketIndex);
        HashMapNode<K, V> node = new HashMapNode<>(key, value);
        node.next = head;
        bucketArray.set(bucketIndex, node);
        double loadFactor = getLoadFactor();
        if (loadFactor > LOAD_FACTOR_THRESHOLD) reHash();
    }

    private void reHash() {
        System.out.println("Rehashing, Size: " + size + " BucketSize: " + bucketSize);
        ArrayList<HashMapNode<K, V>> temp = bucketArray;
        int tempSize = bucketSize;
        bucketArray = new ArrayList<>();
        size = 0;
        bucketSize *= 2;
        initBucket();
        for (int i=0; i<tempSize; i++) {
            HashMapNode<K, V> head = temp.get(i);
            while (head != null) {
                K key = head.key;;
                V value = head.value;
                insert(key, value);
                head = head.next;
            }
        }

    }

    private double getLoadFactor() {
        return ((1.0)*(size))/(bucketSize);
    }

    private int getBucketIndex(K key) {
        int hashCode = key.hashCode();
        return hashCode % bucketSize;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V removeKey(K key) {
        int bucketIndex = getBucketIndex(key);
        HashMapNode<K, V> head = bucketArray.get(bucketIndex);
        HashMapNode<K, V> prev = null;
        while (head != null) {
            if (head.key.equals(key)) {
                size--;
                if (prev == null) {
                    bucketArray.set(bucketIndex, head.next);
                } else {
                    prev.next = head.next;
                }
                return head.value;
            }
            prev = head;
            head = head.next;
        }
        return null;
    }

    @Override
    public V getValue(K key) {
        int bucketIndex = getBucketIndex(key);
        HashMapNode<K, V> head = bucketArray.get(bucketIndex);
        while (head != null) {
            if (head.key.equals(key)) return head.value;
            head = head.next;
        }
        return null;
    }
}
