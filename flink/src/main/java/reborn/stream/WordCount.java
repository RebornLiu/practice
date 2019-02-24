package reborn.stream;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

import java.util.Arrays;

public class WordCount {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> textex = environment.socketTextStream("localhost", 9999, "\n");

        DataStream<Tuple2<String, Integer>> dataStream = textex.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
                Arrays.stream(s.split(" "))
                        .forEach(i -> collector.collect(Tuple2.of(i, 1)));
            }
        }).keyBy(0).sum(1);

        dataStream.print();

        environment.execute("stream task");
    }
}
