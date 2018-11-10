package com.example.selfCache;

import org.springframework.cache.Cache;

import java.util.concurrent.Callable;

public class LocalCache implements Cache {

    @Override
    public String getName() {
        return "localCache";
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Override
    public ValueWrapper get(Object o) {
        System.out.println("localCache get");
        return null;
    }

    @Override
    public <T> T get(Object o, Class<T> aClass) {
        System.out.println("local cahce get");
        return null;
    }

    @Override
    public <T> T get(Object o, Callable<T> callable) {
        System.out.println("local cache get");
        return null;
    }

    @Override
    public void put(Object o, Object o1) {
        System.out.println("local cahe put");
    }

    @Override
    public ValueWrapper putIfAbsent(Object o, Object o1) {
        System.out.println("local cache puifAbsent");
        return null;
    }

    @Override
    public void evict(Object o) {

    }

    @Override
    public void clear() {

    }
}
