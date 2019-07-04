package com.example.springboot;

import com.example.multidatasource.defaultMapper.CustomOrderMapper;
import com.example.multidatasource.slaveMapper.PopCustomOrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootApplicationTests {

    @Autowired
    CacheManager cacheManager;

    @Resource
    private CustomOrderMapper customOrderMapper;

    @Resource
    private PopCustomOrderMapper popCustomOrderMapper;

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

}
