package concurrent;

import java.util.concurrent.Semaphore;

public class SeqDemo {

    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(0);

        Thread thread1 = new Thread(()-> {
            System.out.println("thread1 in");
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread1 in 2");
        });

        Thread thread2 = new Thread(()->{
            System.out.println("thread2 in");
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread2 in 2");
        });

        thread1.start();
        thread2.start();

        System.out.println("main in");
        semaphore.release(2);
        thread1.join();
        thread2.join();
    }
}
