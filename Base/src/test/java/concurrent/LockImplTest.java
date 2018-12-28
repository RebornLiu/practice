package concurrent;

import concurrent.lock.RetrenLockImpl;
import concurrent.lock.SelfLock;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class LockImplTest {

    @Test
    public void lock() throws InterruptedException {

        //SelfLock lock = new LockImpl();

        SelfLock lock = new RetrenLockImpl();
        Thread thread1 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("thread1 get lock");
                TimeUnit.SECONDS.sleep(5);
                System.out.println("thread1 try lock again");
                lock.lock();
            } catch (InterruptedException e) {
                System.out.println("intrupt");
            }
            finally {
                System.out.println("thread1 unlock");
                lock.unlock();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                lock.lock();
                System.out.println("thread2 get lock");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                System.out.println("thread 2 intrrupt");
            }
            finally {
                System.out.println("thread2 unlock");
                lock.unlock();
            }
        });

        thread1.setName("self-thread1");
        thread2.setName("self-thread2");
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }

    @Test
    public void testUtil() throws InterruptedException {
        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
           synchronized (lock) {
               try {
                   TimeUnit.SECONDS.sleep(1000000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }, "self-thread1");


        Thread thread2 = new Thread(() -> {
           synchronized (lock) {
               System.out.println("thread2");
           }
        }, "self-thread2");

        thread1.start();
        TimeUnit.SECONDS.sleep(1);
        thread2.start();

        thread1.join();
        thread2.join();
    }
}