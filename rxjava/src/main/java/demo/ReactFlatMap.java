package demo;

import io.reactivex.Observable;

import java.util.ArrayList;
import java.util.List;


/**
 * flatMap 不保证顺序
 * concatMap 保证顺序
 * */
public class ReactFlatMap {

    public static void main(String[] args) {
        Observable.fromArray(1, 2, 3)
                .flatMap(item -> {
                    List<String> afterMap = new ArrayList<>();
                    for (int i = 0; i < item; i++) {
                        afterMap.add("value " + item + ":" + i);
                    }
                    return Observable.fromArray(afterMap.toArray());
                })
                .subscribe(System.out::println);
    }
}
