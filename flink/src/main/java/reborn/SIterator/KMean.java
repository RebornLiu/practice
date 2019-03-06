package reborn.SIterator;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.functions.RichMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.IterativeDataSet;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.configuration.Configuration;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class KMean {

    public static void main(String[] args) throws Exception {

        List<Point> points = generatorPoint(40);//坐标集
        List<Point> centors = generatorCentor(6);//初始中心点集

        ExecutionEnvironment evn = ExecutionEnvironment.getExecutionEnvironment();
        DataSet<Point> pointSet = evn.fromCollection(points);
        IterativeDataSet<Point> centorSet = evn.fromCollection(centors).iterate(6);

        DataSet<Point> step = pointSet.map(new PCentor())
                .withBroadcastSet(centorSet, "centors")
                .groupBy(0)
                .reduce(new PointSum())
                .map(new PointAvg());

        DataSet<Point> finalCentors = centorSet.closeWith(step);
        finalCentors.print();
    }



    /**
     * 生成坐标点
     * */
    private static List<Point> generatorPoint(int count) {
        return IntStream.rangeClosed(1, count)
                .boxed()
                .map(index -> {
                    Point point = new Point();
                    point.x = Math.random() * 10 + 10;
                    point.y = Math.random() * 10 + 10;
                    return point;
                })
                .collect(Collectors.toList());
    }


    private static List<Point> generatorCentor(int count) {
        return IntStream.rangeClosed(1, count)
                .boxed()
                .map(index -> {
                    Point point = new Point();
                    //区间[10, 20)
                    point.x = Math.random() * 10 + 10;
                    point.y = Math.random() * 10 + 10;
                    point.centorId = index;
                    return point;
                })
                .collect(Collectors.toList());
    }
}

/**
 * 求和坐标值和坐标个数
 * */
class PointSum implements ReduceFunction<Tuple3<Integer, Point, Integer>> {
    @Override
    public Tuple3<Integer, Point, Integer> reduce(Tuple3<Integer, Point, Integer> first,
                                                  Tuple3<Integer, Point, Integer> sec) throws Exception {
        Point tm = new Point();
        tm.x = first.f1.x + sec.f1.x;
        tm.y = first.f1.y + sec.f1.y;
        tm.centorId = first.f0;

        return Tuple3.of(first.f0, tm, first.f2 + sec.f2);
    }
}


/**
 * 根据PointSum计算结果 计算平均值
 * */
class PointAvg implements MapFunction<Tuple3<Integer, Point, Integer>, Point> {
    @Override
    public Point map(Tuple3<Integer, Point, Integer> t) throws Exception {
        Point fP = new Point();
        fP.centorId = t.f0;
        fP.x = t.f1.x/t.f2;
        fP.y = t.f1.y/t.f2;
        return fP;
    }
}


/**
 * 获取最近的中心点，并初始化坐标个数1
 * */
class PCentor extends RichMapFunction<Point, Tuple3<Integer, Point, Integer>> {
    private Collection<Point> centors;
    @Override
    public void open(Configuration parameters) throws Exception {
        centors = getRuntimeContext().getBroadcastVariable("centors");
    }

    @Override
    public Tuple3<Integer, Point, Integer> map(Point point) throws Exception {
        Double dis = Double.MAX_VALUE;
        int closedId = 1;
        Iterator<Point> iterator = centors.iterator();
        while (iterator.hasNext()) {
            Point tmp = iterator.next();
            double tmpDis = distance(tmp, point);
            if (tmpDis < dis) {
                dis = tmpDis;
                closedId = tmp.centorId;
            }
        }

        Point nP = new Point();
        nP.x = point.x;
        nP.y = point.y;
        nP.centorId = closedId;

        return Tuple3.of(closedId, nP, 1);
    }


    private static double distance(Point a, Point b) {
        return Math.sqrt((a.x - b.x)*(a.x - b.x) + (a.y - b.y)*(a.y - b.y));
    }
}

class Point {
    /**
     * 横纵坐标
     * */
    double x;
    double y;

    /**
     * 对应的中心点
     * */
    int centorId;

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}