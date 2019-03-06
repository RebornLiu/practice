package reborn.SIterator;

import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.IterativeDataSet;
import org.apache.flink.configuration.Configuration;

import java.util.Collection;
import java.util.Iterator;

/**
 *
 * 初始
 * base：    2 3 4
 * initial： 1 1 1
 *
 * 第一轮结束
 * base：    2 3 4
 * initial： 3 4 5
 *
 * 第二轮结束
 * base:    2 3 4
 * initial: 3 7 9
 * */
public class StateIteratorDemo {

    public static void main(String[] args) throws Exception {
        ExecutionEnvironment evn = ExecutionEnvironment.getExecutionEnvironment();
        //基础值 不变
        DataSet<NumPoint> base = evn.fromElements(
                new NumPoint(0, 2) ,
                new NumPoint(1, 3),
                new NumPoint(2, 4));
        //迭代值 会迭代变化 接收每次的迭代结果
        IterativeDataSet<NumPoint> initial = evn.fromElements(
                new NumPoint(0, 1),
                new NumPoint(1, 1),
                new NumPoint(2, 1)).iterate(2);

        //将迭代值作为全局状态 这样在StepNum中就可以获取每一次迭代的结果了
        DataSet<NumPoint> step = base.map(new StepSum())
                .withBroadcastSet(initial, "last");

        DataSet<NumPoint> dataSet = initial.closeWith(step);
        dataSet.print();
    }
}


/**
 * 累加计划结果
 * */
class StepSum extends RichMapFunction<NumPoint, NumPoint> {
    private Collection<NumPoint> step;

    @Override
    public void open(Configuration parameters) throws Exception {
        step = getRuntimeContext().getBroadcastVariable("last");
    }

    @Override
    public NumPoint map(NumPoint point) throws Exception {
        Iterator<NumPoint> iterator = step.iterator();
        while (iterator.hasNext()) {
            NumPoint numPoint = iterator.next();
            if (numPoint.index == point.index) {
                return new NumPoint(point.index, point.sum + numPoint.sum);
            }
        }

        return new NumPoint(-1, -1);
    }
}

class NumPoint {
    int index;
    int sum;

    public NumPoint() {
    }

    public NumPoint(int index, int sum) {
        this.index = index;
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "NumPoint{" +
                "index=" + index +
                ", sum=" + sum +
                '}';
    }
}
