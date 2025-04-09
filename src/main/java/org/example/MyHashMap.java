package org.example;

import java.util.Objects;

public class MyHashMap<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private final Entry<K, V>[] table; //main массив
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = new Entry[DEFAULT_INITIAL_CAPACITY];
    }

    //Внутренний класс (одна запись)
    private static class Entry<K, V> {
        final K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public int getSize(){
        return size;
    }

    //добавление новых пар
    public void put(K key, V value) {
        int index = hash(key); //получаем индекс
        Entry<K, V> current = table[index]; //первая запись

        if(current == null){
            table[index] = new Entry<>(key, value); // если ячейка пуста, вставляем запись
            size++;
            return;
        }

        Entry<K, V> prev = null;

        //проходим, чтобы проверить наличие кключа
        while(current != null){
            if(Objects.equals(current.key, key)){
                current.value = value;
                return;
            }
            prev = current;
            current = current.next;
        }
        prev.next = new Entry<>(key, value);
        size++;
    }

    public V get(K key) {
        Entry<K, V> entry = findEntry(key);
        return entry != null ? entry.value : null;
    }

    public void remove(K key) {
        int index = hash(key);
        Entry<K, V> current = table[index];
        Entry<K, V> prev = null;

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    // метод для поиска по ключу
    private Entry<K, V> findEntry(K key){
        int index = hash(key);
        Entry<K, V> current = table[index];

        while (current != null) {
            if (Objects.equals(current.key, key)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    //метод для хеширования ключа
    private int hash(K key) {
        return key == null ? 0 : Math.abs(key.hashCode() % table.length);
    }

    //TODO реализовать resize, containsKey
}
