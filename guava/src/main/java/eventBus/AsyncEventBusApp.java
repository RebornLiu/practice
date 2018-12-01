package eventBus;

import java.util.concurrent.TimeUnit;

public class AsyncEventBusApp {
    public static void main(String[] args) throws InterruptedException {
        BlockedObserverA observerA = new BlockedObserverA();
        BlockedObserverB observerB = new BlockedObserverB();


        observerA.ayncRegist();
        observerB.ayncRegist();

        EventBusInstance.asyncEventBus.post(new Event(222, "async event"));
        System.out.println("after post");

        TimeUnit.MINUTES.sleep(10);
    }
}
