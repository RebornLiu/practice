package com.example.autoware;

import org.springframework.stereotype.Component;

@Component
public class IDemoImpl implements IDemo {
    @Override
    public void echo() {
        System.out.println(IDemoImpl.class.getSimpleName());
    }
}
