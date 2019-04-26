package reborn.join;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.time.Instant;


public class MultiStreamJoinDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> firstStream = environment.socketTextStream("localhost", 9999, "\n");
        DataStreamSource<String> secondStream = environment.socketTextStream("localhost", 9998, "\n");
        SingleOutputStreamOperator<Tuple2<Integer, Integer>> first = firstStream
                .map(new SplitFunction())
                /*.assignTimestampsAndWatermarks(new AscendingTimestampExtractor<Tuple2<Integer, Integer>>() {
                    @Override
                    public long extractAscendingTimestamp(Tuple2<Integer, Integer> integerIntegerTuple2) {
                        return Instant.now().toEpochMilli();
                    }
                })*/
                .returns(new TypeHint<Tuple2<Integer, Integer>>() {});

        SingleOutputStreamOperator<Tuple2<Integer, Integer>> second = secondStream
                .map(new SplitFunction())
               /* .assignTimestampsAndWatermarks(new AscendingTimestampExtractor<Tuple2<Integer, Integer>>() {
                    @Override
                    public long extractAscendingTimestamp(Tuple2<Integer, Integer> integerIntegerTuple2) {
                        return Instant.now().toEpochMilli();
                    }
                })*/
                .returns(new TypeHint<Tuple2<Integer, Integer>>() {});

        first.coGroup(second)
                .where(s -> s.f1)
                .equalTo(s -> s.f0)
                //处理时间
                .window(TumblingProcessingTimeWindows.of(Time.seconds(3)))
                .apply(new MyLeftJoin()).print();

        environment.execute("stream task");
    }
}


class SplitFunction implements MapFunction<String, Tuple2<Integer, Integer>> {
    @Override
    public Tuple2<Integer, Integer> map(String line) throws Exception {
        String[] word = line.split("\\s");
        return Tuple2.<Integer, Integer>of(Integer.valueOf(word[0]), Integer.valueOf(word[1]));
    }
}