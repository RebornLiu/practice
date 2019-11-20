package com.netflix.hystrix;


import com.netflix.hystrix.strategy.HystrixPlugins;
import org.junit.Test;

/**
 * @author by liuweiliang1
 * @date 2019/11/20 10:12
 * @description
 */
public class HystrixCommandTest {

    @Test
    public void testHello() {
        HelloCommon helloCommon = new HelloCommon();
        String rs = helloCommon.execute();
        System.out.println(rs);
    }
}

class HelloCommon extends HystrixCommand<String> {

    public HelloCommon() {
        super(Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey("hello-group"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("hello-comm"))
                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("thread-pool"))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter().withCoreSize(4))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD))
        );
    }
    protected String run() throws Exception {
        Integer i = null;
        if(i == 1) {
            return "hello";
        }
        return "hello in run";
    }


    @Override
    protected String getFallback() {
        return "hello fall back";
    }
}