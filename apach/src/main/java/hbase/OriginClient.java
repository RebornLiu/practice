package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;

public class OriginClient {
    public static void main(String[] args) throws IOException {
        Configuration config = HBaseConfiguration.create();
        config.set("hbase.zookeeper.quorum", "ubuntu.local");
        Connection connection = ConnectionFactory.createConnection(config);
        Table table = connection.getTable(TableName.valueOf("test"));

        Get get = new Get(Bytes.toBytes("rowkey1"));
        get.setMaxVersions();
        Result result = table.get(get);
        byte[] bytes = result.getValue(Bytes.toBytes("cf"), Bytes.toBytes("c1"));
        List<Cell> cellList = result.getColumnCells(Bytes.toBytes("cf"), Bytes.toBytes("c1"));
        for (Cell cell : cellList) {
            System.out.println(Bytes.toString(CellUtil.cloneValue(cell)));
        }
    }
}
