package demo;

import io.reactivex.Observable;

public class ReactError {

    public static void main(String[] args) {
        Observable.create(emitter -> {
            emitter.onNext("before error");
            emitter.onError(new Exception("new exception"));
        })
                .onErrorReturn(r ->
                    Observable.create(emitter -> {
                        emitter.onNext("catch a error");
                        emitter.onComplete();
                    })
                )
                .subscribe(item -> {
                    System.out.println(item);
                });
    }
}
