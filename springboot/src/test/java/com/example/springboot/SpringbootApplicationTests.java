package com.example.springboot;

import com.alibaba.fastjson.JSON;
import com.example.autoware.Container;
import com.example.autoware.IDemo;
import com.example.hbase.HbaseDao;
import com.example.multidatasource.defaultMapper.CustomOrderMapper;
import com.example.multidatasource.slaveMapper.PopCustomOrderMapper;
import com.example.selfCache.Demo;
import com.example.staticdi.Parent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

    @Autowired
    CacheManager cacheManager;

    @Resource
    private CustomOrderMapper customOrderMapper;

    @Resource
    private PopCustomOrderMapper popCustomOrderMapper;

    @Resource
    private Demo demo;

    @Resource
    private Container container;

    @Resource
    private Parent parent;

    @Resource
    private HbaseDao hbaseDao;

    @Test
    public void testHbaseGet() {
        List<String> list = hbaseDao.getCellVal("rowkey1");
        System.out.println(JSON.toJSONString(list));
    }

    @Test
    public void testPostConstruct() {
        parent.echo();
    }

    @Test
    public void contextLoads() {

        cacheManager.getCache("localCache").put(1, 1);
    }

    @Test
    public void testMultiDataSource() {
        int count1 = customOrderMapper.count();
        int count2 = popCustomOrderMapper.count();

        System.out.println(count1);
        System.out.println(count2);
    }

    @Test
    public void testCache() {
        demo.echo("hello");
    }

    @Test
    public void testAutoWare() {
        container.getIDemoList().forEach(IDemo::echo);
    }

}
