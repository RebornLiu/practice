package concurrent;

import concurrent.lock.RWLock;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class RWLockTest {

    @Test
    public void testRWLock() throws InterruptedException {

        RWLock rwLock = new RWLock();
        RWLock.RLock rLock = rwLock.readLock();
        RWLock.WLock wLock = rwLock.writeLock();

        Thread read1 = new Thread(() -> {
            try {
                rLock.lock();
                System.out.println("read-thread-1 read lock");
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                rLock.unlock();
            }
        }, "read-thread-1");

        Thread read2 = new Thread(() -> {
            try {
                rLock.lock();
                System.out.println("read-thread-2 read lock");
                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                rLock.unlock();
            }

        }, "read-thread-2");

        Thread write1 = new Thread(() -> {
            try {
                wLock.lock();
                System.out.println("write-thread-1 write lock");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                wLock.unlock();
            }
        }, "write-thread-1");

        Thread write2 = new Thread(() -> {
            try {
                wLock.lock();
                System.out.println("write-thread-2 write lock");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finally {
                wLock.unlock();
            }
        }, "write-thread-2");

        read1.start();
        TimeUnit.MILLISECONDS.sleep(10);
        read2.start();
        TimeUnit.MILLISECONDS.sleep(10);
        write1.start();
        write2.start();


/*        write1.start();
        TimeUnit.MILLISECONDS.sleep(10);
        write2.start();
        TimeUnit.MILLISECONDS.sleep(10);
        read1.start();
        read2.start();*/

        read1.join();
        read2.join();
        write1.join();
        write2.join();
    }

}