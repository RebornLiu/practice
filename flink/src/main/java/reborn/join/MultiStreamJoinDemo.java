package reborn.join;

import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.functions.CoGroupFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

import javax.annotation.Nullable;
import java.time.Instant;
import java.util.Iterator;
import java.util.function.Consumer;


public class MultiStreamJoinDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();
        environment.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
        //ExecutionConfig executionConfig = environment.getConfig();
        //executionConfig.setAutoWatermarkInterval(5);
        DataStreamSource<String> first = environment.socketTextStream("localhost", 9999, "\n");
        DataStreamSource<String> second = environment.socketTextStream("localhost", 9998, "\n");

        DataStream<Tuple3<Integer, Integer, Integer>> firstStream = first.map(new SplitFunction())
                .assignTimestampsAndWatermarks(new IncreTimeStampExtractor());

        DataStream<Tuple3<Integer, Integer, Integer>> secondStream = second.map(new SplitFunction())
                .assignTimestampsAndWatermarks(new IncreTimeStampExtractor());

        firstStream.coGroup(secondStream)
                .where(s -> s.f1)
                .equalTo(s -> s.f0)
                //处理时间
                .window(TumblingEventTimeWindows.of(Time.minutes(1)))
                //.trigger(EventTimeTrigger.create())
                //30m+10s内出现一次join结果就触发计算，测试ok
                //.trigger(CountTrigger.of(1))

                .apply(new LeftJoin()).print();

        environment.execute("stream task");
    }
}

class LeftJoin implements CoGroupFunction<Tuple3<Integer, Integer, Integer>, Tuple3<Integer, Integer, Integer>, Tuple2<Integer, Integer>> {
    @Override
    public void coGroup(Iterable<Tuple3<Integer, Integer, Integer>> iterable,
                        Iterable<Tuple3<Integer, Integer, Integer>> iterable1,
                        Collector<Tuple2<Integer, Integer>> collector) throws Exception {
        Iterator<Tuple3<Integer, Integer, Integer>> sec = iterable1.iterator();
        System.out.println("----in -----");
        iterable.forEach(new Consumer<Tuple3<Integer, Integer, Integer>>() {
            @Override
            public void accept(Tuple3<Integer, Integer, Integer> currentFirst) {
                if (!sec.hasNext()) {
                    collector.collect(Tuple2.of(currentFirst.f0, null));
                    return;
                }

                while (sec.hasNext()) {
                    collector.collect(Tuple2.of(currentFirst.f0, sec.next().f1));
                }
            }
        });


    }
}

class SplitFunction implements MapFunction<String, Tuple3<Integer, Integer, Integer>> {
    @Override
    public Tuple3<Integer, Integer, Integer> map(String line) throws Exception {
        String[] word = line.split("\\s");
        return Tuple3.<Integer, Integer, Integer>of(Integer.valueOf(word[0]), Integer.valueOf(word[1]), Integer.valueOf(word[2]));
    }
}

class TimeStampExtractor extends  BoundedOutOfOrdernessTimestampExtractor<Tuple3<Integer, Integer, Integer>> {

    public TimeStampExtractor(Time maxOutOfOrderness) {
        super(maxOutOfOrderness);
    }

    @Override
    public long extractTimestamp(Tuple3<Integer, Integer, Integer> integerIntegerIntegerTuple3) {
        return Instant.now().toEpochMilli();
    }
}

class IncreTimeStampExtractor extends AscendingTimestampExtractor<Tuple3<Integer, Integer, Integer>> {


    @Override
    public long extractAscendingTimestamp(Tuple3<Integer, Integer, Integer> integerIntegerIntegerTuple3) {
        return System.currentTimeMillis();
    }
}

class MyWater implements AssignerWithPeriodicWatermarks<Tuple3<Integer, Integer, Integer>> {

    @Nullable
    @Override
    public Watermark getCurrentWatermark() {
        return new Watermark(System.currentTimeMillis());
    }

    @Override
    public long extractTimestamp(Tuple3<Integer, Integer, Integer> integerIntegerIntegerTuple3, long l) {
        return System.currentTimeMillis();
    }
}