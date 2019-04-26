package reborn.join;

import org.apache.flink.api.common.functions.CoGroupFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.CoGroupOperator;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

import java.util.Iterator;

/**
 *
 * coGroup
 * */
public class MultiSetJoinDemo {

    public static void main(String[] args) throws Exception {
        final ExecutionEnvironment executionEnvironment = ExecutionEnvironment.getExecutionEnvironment();
        DataSet<Tuple2<Integer, Integer>> firstSet = executionEnvironment.fromElements(
                Tuple2.of(1, 11),
                Tuple2.of(2, 22),
                Tuple2.of(3, 33));

        DataSet<Tuple2<Integer, Integer>> secSet = executionEnvironment.fromElements(
                Tuple2.of(11, 1),
                Tuple2.of(22, 2),
                Tuple2.of(44, 4),
                Tuple2.of(22, 222));

        CoGroupOperator.CoGroupOperatorSets.CoGroupOperatorSetsPredicate.CoGroupOperatorWithoutFunction rs = firstSet.coGroup(secSet)
                .where(1)
                .equalTo(0);//.with(new printForWatch()).print();

        rs.with(new printForWatch()).print();
        rs.with(new MyRightJoin()).print();
        rs.with(new MyLeftJoin()).print();

    }
}

class printForWatch implements CoGroupFunction<Tuple2<Integer, Integer>, Tuple2<Integer, Integer>, Object> {

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


/**
 * left join
 * */
class MyLeftJoin implements CoGroupFunction<Tuple2<Integer, Integer>, Tuple2<Integer, Integer>, Object> {

    @Override
    public void coGroup(Iterable<Tuple2<Integer, Integer>> iterable, Iterable<Tuple2<Integer, Integer>> iterable1, Collector<Object> collector) throws Exception {
        Iterator<Tuple2<Integer, Integer>> sec = iterable1.iterator();

        iterable.forEach(currentFirst -> {
            if (!sec.hasNext()) {
                collector.collect(Tuple2.of(currentFirst.f0, null));
                return;
            }

            while (sec.hasNext()) {
                collector.collect(Tuple2.of(currentFirst.f0, sec.next().f1));
            }
        });
    }
}

/**
 * right join
 * */
class MyRightJoin implements CoGroupFunction<Tuple2<Integer, Integer>, Tuple2<Integer, Integer>, Object> {

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