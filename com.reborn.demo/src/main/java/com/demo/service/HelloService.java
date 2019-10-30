package com.demo.service;

import java.util.List;

public class HelloService {
    private String msg;
    private List<Integer> numbers;

    public HelloService(String msg, List<Integer> numbers) {
        this.msg = msg;
        this.numbers = numbers;
    }

    public void echo() {
        System.out.println(msg);
        System.out.println(numbers);
    }
}
