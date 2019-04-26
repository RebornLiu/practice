package reborn.join;

import org.apache.flink.api.common.functions.CoGroupFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

import java.util.Iterator;

/**
 * right join
 * */
public class MyRightJoin implements CoGroupFunction<Tuple2<Integer, Integer>, Tuple2<Integer, Integer>, Object> {

    @Override
    public void coGroup(Iterable<Tuple2<Integer, Integer>> iterable, Iterable<Tuple2<Integer, Integer>> iterable1, Collector<Object> collector) throws Exception {
        Iterator<Tuple2<Integer, Integer>> first = iterable.iterator();

        iterable1.forEach(currentSec -> {
            //left is empty
            if (!first.hasNext()) {
                collector.collect(Tuple2.of(null, currentSec.f1));
                return;
            }

            //left is not empty
            while (first.hasNext()) {
                collector.collect(Tuple2.of(first.next().f0, currentSec.f1));
            }
        });
    }
}
