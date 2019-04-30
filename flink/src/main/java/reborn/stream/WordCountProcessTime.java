package reborn.stream;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

import java.util.Arrays;

public class WordCountProcessTime {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        //如果使用事件事件 需要设置 否则无效
        environment.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);
        environment.socketTextStream("localhost", 9999, "\n")
                .flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
                    @Override
                    public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
                        Arrays.stream(s.split(" "))
                                .forEach(i -> collector.collect(Tuple2.of(i, 1)));
                    }
                })
                .keyBy(0)
                .timeWindow(Time.seconds(5))
                .sum(1).print();

        environment.execute("stream task");
    }
}
