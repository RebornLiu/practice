package demo;


import io.reactivex.Observable;

/**
 * 连接连个observable
 * 1.第一个observable完成后，会发射第二个observabele的内容，因此第一个observable必须调用onComplete
 * 2.异常会中断发射
 * */
public class ReactConcat {

    public static void main(String[] args) {
        Observable<String> first = Observable.create(emitter -> {
            emitter.onNext("first1");
            //emitter.onError(new Exception("error"));
            emitter.onComplete();
        });

        Observable<String> second = Observable.create(emitter -> {
            emitter.onNext("second1");
            emitter.onComplete();
        });

        Observable.concat(first, second)
                .subscribe(System.out::println);
    }
}
