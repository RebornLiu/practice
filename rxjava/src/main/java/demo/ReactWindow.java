package demo;


import io.reactivex.Observable;

import java.util.concurrent.TimeUnit;


/**
 * window产生的是observable 也就是subscribe的入参是Observable，并不是发射的对象
 * */
public class ReactWindow {

    public static void main(String[] args) throws InterruptedException {
        Observable.interval(1, TimeUnit.SECONDS)
                .window(3, TimeUnit.SECONDS)
                .subscribe(subObservable -> {
                    System.out.println("++++++");
                    subObservable.subscribe(r -> System.out.println("da da da"));
                });
        Thread.sleep(100000);
    }
}
