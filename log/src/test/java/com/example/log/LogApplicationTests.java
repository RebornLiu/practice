package com.example.log;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest
public class LogApplicationTests {

    @Resource
    private LogPrinter logPrinter;

    @Test
    public void changeLoglevel() {

        System.out.println("------------------set logger level debug---------------");
        LogManager.getRootLogger().setLevel(Level.DEBUG);
        logPrinter.debugLevel();
        logPrinter.infoLevel();

        System.out.println("------------------set logger level info----------------");

        LogManager.getRootLogger().setLevel(Level.INFO);
        logPrinter.debugLevel();
        logPrinter.infoLevel();
    }


}
