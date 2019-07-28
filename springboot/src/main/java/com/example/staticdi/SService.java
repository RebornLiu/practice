package com.example.staticdi;

import org.springframework.stereotype.Component;

@Component
public class SService {
    public void hello() {
        System.out.println("s service hello");
    }
}
