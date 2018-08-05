package demo;

import io.reactivex.Observable;

public class ReactDistinct {

    public static void main(String[] args) {
        Observable.fromArray(1, 2, 3, 2, 1, 4)
                .distinct()
                .subscribe(System.out::println);
    }
}
