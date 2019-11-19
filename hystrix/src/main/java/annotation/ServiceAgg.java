package annotation;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

/**
 * @author by liuweiliang1
 * @date 2019/11/10 18:47
 * @description
 */
public class ServiceAgg {

    @HystrixCommand(
            groupKey = "agg",
            commandKey = "aggkey",
            threadPoolKey = "aggpool",
            fallbackMethod = "fallback",
            commandProperties = {
                    @HystrixProperty(value = "1000", name = "execution.isolation.thread.timeoutInMilliseconds")
            },
            threadPoolProperties = {
                    @HystrixProperty(value = "4", name = "hystrix.threadpool.default.coreSize")
            }
    )
    public String echo(String msg) {
        return null;
    }

    public String fallback(String msg) {
        return msg;
    }
}
