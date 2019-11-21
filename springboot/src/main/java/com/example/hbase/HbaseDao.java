package com.example.hbase;

import com.alibaba.fastjson.JSON;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.RowMapper;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class HbaseDao {

    @Resource
    private HbaseTemplate hbaseTemplate;

    public List<String> getCellVal(String rowKey) {
        List<String> rs = hbaseTemplate.get("test", rowKey, "cf", "c1", new RowMapper<List<String>>() {

            @Override
            public List<String> mapRow(Result result, int i) throws Exception {
                Cell[] cells = result.rawCells();
                List<String> rs = new ArrayList<>();
                for (Cell c : cells) {
                    rs.add(Bytes.toString(CellUtil.cloneValue(c)));
                }

                return rs;
            }
        });

        return rs;
    }
}
