package com.example.schedule.handler;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class SecondHandler {

    //@Async("pool")
    @Scheduled(fixedRate = 5000L)
    public void handle() {
        System.out.println("second");

      /*  try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

    }
}
