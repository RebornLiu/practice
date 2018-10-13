package concurrent;

import java.util.concurrent.TimeUnit;

public class Sync {

    public static void main(String[] args) throws InterruptedException {
        Object mutex = new Object();

        Thread thread = new Thread(() -> {
           synchronized (mutex) {
               try {
                   mutex.wait();
               } catch (InterruptedException e) {
                   System.out.println("inttrupt");
               }
/*               while (true) {
                   System.out.println(1111);
               }*/
           }
        });

        thread.start();
        TimeUnit.MILLISECONDS.sleep(2);
        thread.interrupt();
        //thread.join();
    }
}
