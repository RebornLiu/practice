package com.example.selfauto;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AutoClientServiceTest {

    @Resource
    private AutoClientService autoClientService;

    @Test
    public void testEcho() {
        autoClientService.hello();
    }
}