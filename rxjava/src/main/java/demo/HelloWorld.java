package demo;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.observers.BlockingObserver;

import java.util.Random;

public class HelloWorld {
    public static void main(String[] args) {
        Observable observable = Observable.create(emmiter -> {
            emmiter.onNext("斗破苍穹");
            emmiter.onNext("木升级");
            //emmiter.onComplete();
            emmiter.onError(new Exception("testException"));
        });
        observable
                .map(r -> "<<" + r + ">>")
                .subscribe(System.out::println, System.out::print);

    }
}
