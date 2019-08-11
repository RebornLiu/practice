package cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheDemo {

    public static void main(String[] args) {
        Cache<String, String> cache = CacheBuilder.newBuilder()
                .maximumSize(10)
                .build();

        String str = cache.getIfPresent("tst");
        System.out.println(str);
        cache.put("tst", "testtttt");
        System.out.println(cache.getIfPresent("tst"));

    }
}
