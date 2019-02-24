package reborn.batch;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.util.Collector;

import java.util.Arrays;

public class WordCountStr {

    public static void main(String[] args) throws Exception {
        final ExecutionEnvironment executionEnvironment = ExecutionEnvironment.getExecutionEnvironment();
        DataSet<String> text = executionEnvironment.fromElements("Who's there?",
                "I think I hear them. Stand, ho! Who's there?");

        DataSet<Tuple2<String, Integer>> wordCounts = text.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String line, Collector<Tuple2<String, Integer>> collector) throws Exception {
                Arrays.stream(line.split(" "))
                        .forEach(item -> collector.collect(Tuple2.of(item, 1)));
            }
        }).groupBy(0).sum(1);

        //文件系统路径
        // https://ci.apache.org/projects/flink/flink-docs-release-1.7/ops/filesystems.html
        wordCounts.writeAsText("file:///home/reborn/work/text", FileSystem.WriteMode.OVERWRITE);

        executionEnvironment.execute("my word count");
    }
}
