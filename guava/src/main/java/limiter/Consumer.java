package limiter;

import com.google.common.util.concurrent.RateLimiter;

public class Consumer {

    public void consumer() {
        System.out.println(Limiter.limiter.acquire());
    }
}
