package reborn.join;

import org.apache.flink.api.common.functions.CoGroupFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class printForWatch implements CoGroupFunction<Tuple2<Integer, Integer>, Tuple2<Integer, Integer>, Object> {

    @Override
    public void coGroup(Iterable<Tuple2<Integer, Integer>> iterable, Iterable<Tuple2<Integer, Integer>> iterable1, Collector<Object> collector) throws Exception {
        System.out.println("---------------");
        for (Tuple2<Integer, Integer> integerIntegerTuple2 : iterable) {
            System.out.println(integerIntegerTuple2);
        }

        for (Tuple2<Integer, Integer> integerIntegerTuple2 : iterable1) {
            System.out.println(integerIntegerTuple2);
        }
    }

}
