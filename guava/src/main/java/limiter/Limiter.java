package limiter;

import com.google.common.util.concurrent.RateLimiter;

public class Limiter {
    public static final RateLimiter limiter = RateLimiter.create(1);
}
