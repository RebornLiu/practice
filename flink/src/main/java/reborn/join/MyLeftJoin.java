package reborn.join;

import org.apache.flink.api.common.functions.CoGroupFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

import java.util.Iterator;
import java.util.function.Consumer;

/**
 * left join
 * */
public class MyLeftJoin implements CoGroupFunction<Tuple2<Integer, Integer>, Tuple2<Integer, Integer>, Tuple2<Integer, Integer>> {

    @Override
    public void coGroup(Iterable<Tuple2<Integer, Integer>> iterable, Iterable<Tuple2<Integer, Integer>> iterable1, Collector<Tuple2<Integer, Integer>> collector) throws Exception {
        Iterator<Tuple2<Integer, Integer>> sec = iterable1.iterator();

        iterable.forEach(new Consumer<Tuple2<Integer, Integer>>() {
            @Override
            public void accept(Tuple2<Integer, Integer> currentFirst) {
                if (!sec.hasNext()) {
                    collector.collect(Tuple2.of(currentFirst.f0, null));
                    return;
                }

                while (sec.hasNext()) {
                    collector.collect(Tuple2.of(currentFirst.f0, sec.next().f1));
                }
            }
        } );
    }
}
