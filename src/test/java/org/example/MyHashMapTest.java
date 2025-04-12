package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyHashMapTest {

    @Test
    void getSize() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        assertEquals(0, map.getSize());

        map.put("one", 1);
        map.put("two", 2);
        assertEquals(2, map.getSize());

        map.remove("one");
        assertEquals(1, map.getSize());
    }

    @Test
    void put() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        assertEquals("value1", map.get("key1"));
        assertEquals("value2", map.get("key2"));

        map.put("key1", "newValue");
        assertEquals("newValue", map.get("key1"));
    }

    @Test
    void get() {
        MyHashMap<String, String> map = new MyHashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");

        assertEquals("value1", map.get("key1"));
        assertEquals("value2", map.get("key2"));
        assertNull(map.get("notExi"));

    }

    @Test
    void remove() {
        MyHashMap<String, Integer> map = new MyHashMap<>();
        map.put("key1", 1);
        map.put("key2", 2);

        assertEquals(2, map.getSize());
        map.remove("key1");

        assertNull(map.get("key1"));
        assertEquals(1, map.getSize());

        map.remove("notExi");
        assertEquals(1, map.getSize());
    }

    @Test
    void resize() {
        MyHashMap<Integer, String> map = new MyHashMap<>();

        for (int i = 0; i < 20; i++) {
            map.put(i, "value" + i);
        }

        for (int i = 0; i < 20; i++) {
            assertEquals("value" + i, map.get(i));
        }

        assertEquals(20, map.getSize());
    }
}