package concurrent.lock;

import java.time.Clock;
import java.time.ZoneId;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeoutException;


/**
 * 基于synchronized的不可重入的锁
 *
 * */
public class LockImpl implements SelfLock {

    private Thread currentHolder;

    private boolean locked = false;

    private Set<Thread> waitSet = new HashSet<>();

    @Override
    public void lock() throws InterruptedException {
        synchronized (this) {
            while (locked) {
                waitSet.add(Thread.currentThread());
                this.wait();
            }

            waitSet.remove(Thread.currentThread());
            locked = true;
            currentHolder = Thread.currentThread();
        }
    }

    @Override
    public synchronized void unlock() {
        if (Thread.currentThread() == currentHolder) {
            locked = false;
            currentHolder = null;
            this.notifyAll();
        }
    }

    @Override
    public void lock(long time) throws InterruptedException, TimeoutException {
        if (time <= 0) {
            lock();
            return;
        }

        synchronized (this) {
            long remainTime = time;
            long endTime = Clock.system(ZoneId.systemDefault()).millis() + remainTime;
            while (locked) {
                waitSet.add(Thread.currentThread());
                this.wait(remainTime);
                remainTime = endTime - Clock.system(ZoneId.systemDefault()).millis();
                if (remainTime <0) {
                    throw new TimeoutException("time out");
                }
            }
            locked = true;
            waitSet.remove(Thread.currentThread());
            currentHolder = Thread.currentThread();
        }


    }
}
