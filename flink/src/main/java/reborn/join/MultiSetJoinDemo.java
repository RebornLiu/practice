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


