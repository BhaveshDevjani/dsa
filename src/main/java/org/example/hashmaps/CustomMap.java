package org.example.hashmaps;

public interface CustomMap<K, V> {

    void insert(K key, V value);

    int size();

    V removeKey(K key);

    V getValue(K key);

}
