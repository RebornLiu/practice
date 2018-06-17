package com.self;

import com.netflix.hystrix.*;

public class Client extends HystrixCommand<String> {
    private String type;

    public Client(String type) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"))  //必须
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationThreadTimeoutInMilliseconds(500))  //超时时间
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("ExampleGroup-pool"))  //可选,默认 使用 this.getClass().getSimpleName();
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(4)));
        this.type = type;
    }

    protected String run() throws Exception {
        if ("exception".equals(type)) {
            throw new IllegalArgumentException("exception");
        }
        else if ("timeout".equals(type)) {
            Thread.sleep(1000);
        }
        return "run";
    }

    @Override
    protected String getFallback() {
        boolean timeout = isResponseTimedOut();
        boolean exception = isFailedExecution();
        if (timeout) {
            return "timeout";
        }
        else if (exception) {
            return "exception";
        }
        return "fail";
    }
}
