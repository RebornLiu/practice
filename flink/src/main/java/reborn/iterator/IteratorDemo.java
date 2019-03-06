package reborn.iterator;

import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.IterativeDataSet;

public class IteratorDemo {

    public static void main(String[] args) throws Exception {
        final ExecutionEnvironment evn = ExecutionEnvironment.getExecutionEnvironment();

        //初始化集合 指定迭代次数
        IterativeDataSet<Integer> initDataSet = evn.fromElements( 1, 2 ,3 , 4, 5).iterate(4);

        //迭代操作
        DataSet<Integer> step = initDataSet.map(i -> i + 1);

        //获取迭代结果
        DataSet<Integer> finalRs = initDataSet.closeWith(step);

        finalRs.print();

        evn.execute();
    }
}
