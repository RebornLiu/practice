package com.example.selfCache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableCaching
public class CacheManagerConfig {

    @Bean("cacheManager")
    public CacheManager cacheManager() {
        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
        LocalCache localCache = new LocalCache();
        List<Cache> caches = new ArrayList<>();
        caches.add(localCache);
        simpleCacheManager.setCaches(caches);
        return simpleCacheManager;
    }
}
