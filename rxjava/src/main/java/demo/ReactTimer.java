package demo;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.internal.schedulers.SingleScheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * 延迟执行
 * */
public class ReactTimer {

    public static void main(String[] args) throws InterruptedException {
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribe(r -> System.out.println("async handler"));

        Thread.sleep(1000000);
    }
}
