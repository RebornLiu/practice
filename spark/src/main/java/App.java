import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;

public class App {

    public static void main(String[] args) {
        String logFile = "E:\\WORKPLACE\\practice\\spark\\src\\main\\java\\readme.md"; // Should be some file on your system
        SparkSession spark = SparkSession.builder().appName("Simple Application").getOrCreate();
        Dataset<String> logData = spark.read().textFile(logFile).cache();

        long numAs = logData.filter((String s) -> s.contains("1")).count();
        long numBs = logData.filter((String s) -> s.contains("3")).count();

        System.out.println("Lines with a: " + numAs + ", lines with b: " + numBs);

        spark.stop();
    }
}
