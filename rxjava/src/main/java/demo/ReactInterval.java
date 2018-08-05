package demo;


import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.TestScheduler;

import java.util.concurrent.TimeUnit;

public class ReactInterval {

    public static void main(String[] args) throws InterruptedException {
        Observable.interval(1, 1, TimeUnit.SECONDS)
                .subscribe(r -> System.out.println("interval"));

        Thread.sleep(10000);
    }
}
