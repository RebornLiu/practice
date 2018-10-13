package hook;

import java.util.concurrent.TimeUnit;

public class Hook {

    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("out");
        }));

        for (;;) {
            System.out.println("for");
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
