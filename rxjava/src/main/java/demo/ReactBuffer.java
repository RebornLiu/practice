package demo;

import io.reactivex.Observable;


/**
 * 有点像分组
 * */
public class ReactBuffer {
    public static void main(String[] args) {
        Observable.fromArray(1, 2, 3, 4, 5, 6, 7)
                .buffer(3, 2)
                .subscribe(list -> {
                    list.forEach(System.out::println);
                    System.out.println("------------");
                });
    }
}
