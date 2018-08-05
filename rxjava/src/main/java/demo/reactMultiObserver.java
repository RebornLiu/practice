package demo;

import io.reactivex.Observable;

public class reactMultiObserver {

    public static void main(String[] args) {
        Observable<String> observable = Observable.create(emmiter -> {
            emmiter.onNext("hello");
            emmiter.onNext("world");
            emmiter.onComplete();
        });

        observable.subscribe(System.out::println).dispose();
        observable.map(r-> r + " test")
        .subscribe(System.out::println).dispose();
    }
}
