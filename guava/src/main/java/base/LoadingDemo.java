package base;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class LoadingDemo {

    public static void main(String[] args) {
        Cache<String, String> loadingCache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .build();

        String str = loadingCache.getIfPresent("tst");
        System.out.println(str);
        loadingCache.put("tst", "testtttt");
        System.out.println(loadingCache.getIfPresent("tst"));

    }
}
