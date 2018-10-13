package concurrent;

import java.rmi.AccessException;
import java.time.Clock;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeoutException;


/**
 * 基于synchronized的可重入锁
 * */
public class RetrenLockImpl implements SelfLock{

    private Thread currentHolder;

    private boolean locked;

    private Set<Thread> waitingSet = new HashSet<>();

    @Override
    public synchronized void lock() throws InterruptedException {
        if (currentHolder == Thread.currentThread()) {
            return;
        }

        while (locked) {
            waitingSet.add(Thread.currentThread());
            this.wait();
        }

        locked = true;
        currentHolder = Thread.currentThread();
        waitingSet.remove(Thread.currentThread());
    }

    @Override
    public synchronized void unlock() {
        if (currentHolder == Thread.currentThread()) {
            locked = false;
            currentHolder = null;
            this.notifyAll();
        }
    }

    @Override
    public void lock(long time) throws InterruptedException, TimeoutException {

        if(time <= 0) {
            lock();
            return;
        }

        synchronized (this) {
            if (currentHolder == Thread.currentThread()) {
                return;
            }

            long expire = Clock.systemDefaultZone().millis() + time;
            long waitTime = time;
            while (locked) {
                waitingSet.add(Thread.currentThread());
                this.wait(waitTime);
                waitTime = expire - Clock.systemDefaultZone().millis();
                if (waitTime <= 0) {
                    throw new TimeoutException("time out ");
                }
            }

            locked = true;
            currentHolder = Thread.currentThread();
            waitingSet.remove(Thread.currentThread());
        }
    }
}
