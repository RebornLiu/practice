import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MultiPrinter {
    private Lock lock = new ReentrantLock();
    private Condition firstDoneCon = lock.newCondition();
    private Condition secondDoneCon = lock.newCondition();
    public MultiPrinter() {
    }


    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        lock.lock();
        printFirst.run();
        lock.unlock();
        firstDoneCon.signal();
    }

    public void second(Runnable printSecond) throws InterruptedException {

        // printSecond.run() outputs "second". Do not change or remove this line.
        lock.lock();
        firstDoneCon.await();
        printSecond.run();
        secondDoneCon.signal();
        lock.unlock();

    }

    public void third(Runnable printThird) throws InterruptedException {

        // printThird.run() outputs "third". Do not change or remove this line.
        lock.lock();
        secondDoneCon.await();
        printThird.run();
        lock.unlock();

    }
}
