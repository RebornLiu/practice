package concurrent;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeoutException;

public class RWLock {

    private int readNum = 0;
    private boolean writeLock = false;
    private Set<Thread> writeWaiting = new HashSet<>();
    private Set<Thread> readWaiting = new HashSet<>();
    private Thread writeHolder;
    private Set<Thread> readHolders = new HashSet<>();

    private Object mutex = new Object();

    private RLock rLock = new RLock();

    private WLock wLock = new WLock();

    public RLock readLock() {
        return rLock;
    }

    public WLock writeLock() {
        return wLock;
    }

    class RLock implements SelfLock {
        @Override
        public void lock() throws InterruptedException {
            synchronized (mutex) {
                while (writeLock) {
                    readWaiting.add(Thread.currentThread());
                    mutex.wait();
                }
                if (readHolders.add(Thread.currentThread())) {
                    readNum++;
                }
                readWaiting.remove(Thread.currentThread());
            }
        }

        @Override
        public void unlock() {
            synchronized (mutex) {
                if (readHolders.remove(Thread.currentThread())) {
                    readNum--;
                }
                if (readNum == 0) {
                    mutex.notifyAll();
                }
            }
        }

        @Override
        public void lock(long time) throws InterruptedException, TimeoutException {

        }
    }

    public class WLock implements SelfLock {
        @Override
        public void lock() throws InterruptedException {
            synchronized (mutex) {
                while (readNum > 0 || writeLock) {
                    writeWaiting.add(Thread.currentThread());
                    mutex.wait();
                }

                writeLock = true;
                writeWaiting.remove(Thread.currentThread());
                writeHolder = Thread.currentThread();
            }
        }

        @Override
        public void unlock() {
            synchronized (mutex) {
                if (Thread.currentThread() == writeHolder) {
                    writeLock = false;
                    writeHolder = null;
                    mutex.notifyAll();
                }
            }
        }

        @Override
        public void lock(long time) throws InterruptedException, TimeoutException {

        }
    }
}
