package demo;

import io.reactivex.Observable;

/**
 * reduce将计算结果发射出去
 * scan 把每一次i的计算结果都发射出去
 * */
public class ReactReduceScan {

    private static void reduceTest() {
        Observable.fromArray(1, 2, 3, 4)
                .reduce(1, (first, second)-> first + second)
                .subscribe(System.out::println);
    }

    private static void scanTest() {
        Observable.fromArray(1, 2, 3, 4)
                .scan(1, (first, second) -> first + second)
                .subscribe(System.out::println);
    }

    public static void main(String[] args) {
        System.out.println("----reduce------");
        reduceTest();
        System.out.println("----scan----");
        scanTest();
    }
}
