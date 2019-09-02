import java.util.concurrent.Semaphore;

public class H2O {
    private Semaphore oSem = new Semaphore(0);
    private Semaphore hSem = new Semaphore(2);

    public H2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {

        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        hSem.acquire(1);
        releaseHydrogen.run();
        oSem.release(1);
    }


    public void oxygen(Runnable releaseOxygen) throws InterruptedException {

        // releaseOxygen.run() outputs "H". Do not change or remove this line.
        oSem.acquire(2);
        releaseOxygen.run();
        hSem.release(2);
    }
}
