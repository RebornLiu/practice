package jdk8;

import jdk8.impl.TestImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class App {

    public static void main(String[] args) {
        TestImpl test = new TestImpl();
        test.selfEcho();

        Collector.of(ArrayList::new, List::add, (l, r) -> {l.addAll(r); return l;});
    }
}
