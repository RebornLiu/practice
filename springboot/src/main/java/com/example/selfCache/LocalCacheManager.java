package com.example.selfCache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;


public class LocalCacheManager implements CacheManager {
    @Override
    public Cache getCache(String s) {
        return new LocalCache();
    }

    @Override
    public Collection<String> getCacheNames() {
        return new ArrayList<>(Arrays.asList("localCache"));
    }
}
