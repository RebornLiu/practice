package com.example.staticdi;

import org.springframework.stereotype.Component;

@Component
public class Service {

    public void service() {
        System.out.println("i am service");
    }
}
