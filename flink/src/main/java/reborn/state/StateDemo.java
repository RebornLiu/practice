package reborn.state;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.configuration.Configuration;

import java.util.Collection;

public class StateDemo {

    public static void main(String[] args) throws Exception {
        ExecutionEnvironment evn = ExecutionEnvironment.getExecutionEnvironment();
        //全局状态 是个dataSet
        DataSet<Integer> state = evn.fromElements(1);
        DataSet<Integer> dataSet = evn.fromElements(1, 2, 3 ,4 ,5);
        //第一个map算子获取了全局变量
        DataSet<Integer> rs = dataSet.map(new StateFull())
                .withBroadcastSet(state, "last")
                .map(i -> i + 10);

        rs.print();
    }

}

class StateFull extends RichMapFunction<Integer, Integer> {

    private Collection<Integer> rs;

    @Override
    public void open(Configuration parameters) throws Exception {
        rs = getRuntimeContext().getBroadcastVariable("last");
    }

    @Override
    public Integer map(Integer integer) throws Exception {
        return integer + rs.iterator().next();
    }
}
