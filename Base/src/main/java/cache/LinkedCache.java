package cache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author by liuweiliang1
 * @date 2019/10/16 9:56
 * @description
 */
public class LinkedCache {

    private LinkedHashMap<String, String> container;
    private final int capacity;
    private int count;

    public LinkedCache(int cap) {
        if (container == null) {
            container = new LinkedHashMap<String, String>(cap, .75f, true) {
                @Override
                protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
                    if (super.size() > cap) {
                        super.remove(eldest.getKey());
                    }
                    return true;
                }
            };
        }
        this.capacity = cap;
        count = 0;
    }


    public boolean put(String key, String value) {
        container.put(key, value);
        return true;
    }

    private String get(String key) {
        return container.get(key);
    }
}
