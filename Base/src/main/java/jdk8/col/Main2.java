package jdk8.col;

import java.util.ArrayList;
import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("first");
        list.add("second");
        list.add("third");
        list.add("fourth");

        IndexIterator.index(list, (index, value) -> {
            System.out.println(index);
            System.out.println(value);

            return value + "test";
        }).forEach(System.out::println);
    }
}
