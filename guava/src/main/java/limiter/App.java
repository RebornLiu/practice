package limiter;

import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) throws InterruptedException {
        Consumer consumer = new Consumer();
        for (int i = 1; i < 100; i ++) {
            new Thread(consumer::consumer).start();
        }

        TimeUnit.DAYS.sleep(1);
    }
}
