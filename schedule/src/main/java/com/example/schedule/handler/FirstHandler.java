package com.example.schedule.handler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class FirstHandler {

    //@Async("pool")
    @Scheduled(fixedRate = 5000L)
    public void handle() {
        System.out.println("first");
        try {
           // TimeUnit.SECONDS.sleep(5L);
        }
        catch (Exception e) {
            e.printStackTrace();

        }
        throw new RuntimeException("232");
    }
}
