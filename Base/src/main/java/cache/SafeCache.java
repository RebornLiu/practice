package cache;

import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author by liuweiliang1
 * @date 2019/10/16 11:48
 * @description
 */
public class SafeCache {

    private ConcurrentHashMap<String, String> container;
    private Lock mapLock = new ReentrantLock();
    private LinkedList<String> linkedList = new LinkedList<>();
    private Lock listLock = new ReentrantLock();

    private int cap;


    public void put(String key, String value) {
        mapLock.lock();
        try {
            if (container.size() >= cap && !container.containsKey(key)) {
                container.remove(linkedList.getFirst());
            }
        }
        finally {
            mapLock.unlock();
        }

        container.put(key, value);
        listLock.lock();
        try {
            linkedList.remove(key);
            linkedList.addLast(key);
        }
        finally {
            listLock.unlock();
        }
    }
}
