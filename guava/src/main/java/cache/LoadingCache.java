package cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

import java.util.concurrent.ExecutionException;

public class LoadingCache {

    public static void main(String[] args) throws ExecutionException {
        com.google.common.cache.LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        return null;
                    }
                });

        loadingCache.get("112");
    }
}
