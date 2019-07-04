package jdk8.col;

import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IndexIterator {

    public static <T, E> Stream<E> index(List<T> list, BiFunction<Integer, T, E> function) {
        int size = list.size();
        return IntStream.range(0, size - 1)
                .boxed()
                .map(index -> function.apply(index, list.get(index)));
    }

    public static <T, E> void forEach(List<T> list, BiFunction<Integer, T, E> function) {
        int size = list.size();
        IntStream.range(0, size - 1)
                .boxed()
                .forEach(index -> function.apply(index, list.get(index)));
    }
}
