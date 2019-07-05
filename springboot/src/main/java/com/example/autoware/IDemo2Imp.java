package com.example.autoware;

import org.springframework.stereotype.Component;

@Component
public class IDemo2Imp implements IDemo {
    @Override
    public void echo() {
        System.out.println(IDemo2Imp.class.getSimpleName());
    }
}
