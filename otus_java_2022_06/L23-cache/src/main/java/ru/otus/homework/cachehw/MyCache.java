package ru.otus.homework.cachehw;


import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

public class MyCache<K, V> implements HwCache<K, V> {
//Надо реализовать эти методы

    private final WeakHashMap<K, V> cacheHashMap;
    private final List<HwListener<K, V>> listeners;

    public MyCache() {
        this.cacheHashMap = new WeakHashMap<>();
        this.listeners = new ArrayList<>();
    }

    @Override
    public void put(K key, V value) {
        cacheHashMap.put(key, value);
        listeners.forEach(listener -> listener.notify(key,value,"put") );
    }

    @Override
    public void remove(K key) {
        cacheHashMap.remove(key);
        listeners.forEach(listener -> listener.notify(key,null,"remove") );
    }

    @Override
    public V get(K key) {
        if (cacheHashMap.containsKey(key)) {
            V value = cacheHashMap.get(key);
            listeners.forEach(listener -> listener.notify(key,value,"get") );
            return value;
        } else {
            throw new NoSuchFieldError();
        }
    }

    @Override
    public void addListener(HwListener<K, V> listener) {
        this.listeners.add(listener);
    }

    @Override
    public void removeListener(HwListener<K, V> listener) {
        this.listeners.remove(listener);
    }
}
