package concurrent.lock;

import java.rmi.AccessException;
import java.util.concurrent.TimeoutException;

public interface SelfLock {
    void lock() throws InterruptedException;

    void unlock();

    void lock(long time) throws InterruptedException, TimeoutException;
}
