package concurrent.lock;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomDemo {

    public static void main(String[] args) {
        Random random = ThreadLocalRandom.current();
        Random random1 = ThreadLocalRandom.current();
        random.nextInt();
        System.out.println(random);
        System.out.println(random1);
    }
}
