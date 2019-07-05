package com.example.selfCache;

import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;

public class LocalCache implements Cache {

    private static ConcurrentHashMap<Object, Object> map = new ConcurrentHashMap<>();

    @Override
    public String getName() {
        return "self-cache";
    }

    @Override
    public Object getNativeCache() {
        return map;
    }

    @Override
    public ValueWrapper get(Object o) {
        return new SimpleValueWrapper(map.get(o));
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        return aClass.cast(map.get(o));
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void put(Object o, Object o1) {
        System.out.println("put method");
        map.put(o, o1);
    }

    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        System.out.println("putIfAbsent method");
        Object old = map.putIfAbsent(o, o1);
        return new SimpleValueWrapper(old);
    }

    @Override
    public void evict(Object o) {
        map.remove(o);
    }

    @Override
    public void clear() {
        map.clear();
    }
}
