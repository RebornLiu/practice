package reborn.stream;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.CountTrigger;
import org.apache.flink.streaming.api.windowing.triggers.EventTimeTrigger;
import org.apache.flink.util.Collector;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.Arrays;

public class WordCountEventTime {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        //如果使用事件事件 需要设置 否则无效
        environment.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        environment.socketTextStream("localhost", 9999, "\n")
                .flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
                    @Override
                    public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
                        Arrays.stream(s.split(" "))
                                .forEach(i -> collector.collect(Tuple2.of(i, 1)));
                    }
                })
                /**
                 * 允许延迟30s+5s 也就是每35s输出一次
                 * */
                .assignTimestampsAndWatermarks(new MyWaterGenerator())
                .keyBy(0)
                .timeWindow(Time.seconds(5))
                .trigger(EventTimeTrigger.create())
                .sum(1).print();

        environment.execute("stream task");
    }
}


class MyWaterGenerator implements AssignerWithPeriodicWatermarks<Tuple2<String, Integer>> {
    @Nullable
    @Override
    public Watermark getCurrentWatermark() {
        return new Watermark(System.currentTimeMillis() - 20 * 1000);
    }

    @Override
    public long extractTimestamp(Tuple2<String, Integer> stringIntegerTuple2, long l) {
        return System.currentTimeMillis();
    }
}
