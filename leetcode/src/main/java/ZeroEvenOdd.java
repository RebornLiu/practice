import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

class ZeroEvenOdd {
    private int n;
    private Semaphore semaphoreZ = new Semaphore(1);
    private Semaphore semaphoreE = new Semaphore(0);
    private Semaphore semaphoreO = new Semaphore(0);
    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i ++) {
            semaphoreZ.acquire();
            printNumber.accpet(0);
            if ((i & 0x1) == 0) {
                semaphoreE.release();
            }
            else {
                semaphoreO.release();
            }
        }
    }

    //偶数
    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n;) {
            semaphoreE.acquire();
            printNumber.accpet(i);
            i = i + 2;
            semaphoreZ.release();
        }
    }

    //奇数
    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int  i = 1; i <= n;) {
            semaphoreO.acquire();
            printNumber.accpet(i);
            i = i + 2;
            semaphoreZ.release();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ZeroEvenOdd zeroEvenOdd = new ZeroEvenOdd(4);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.zero(new IntConsumer());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.even(new IntConsumer());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    zeroEvenOdd.odd(new IntConsumer());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}



class IntConsumer {
    void accpet(Integer i) {
        System.out.println(i);
    }

}
