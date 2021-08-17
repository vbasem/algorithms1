package problem;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LogRateLimiter {

    static long SECONDS_INTERVAL = 10;
    Map<String, Long> cache;

    public LogRateLimiter() {
        cache = new ConcurrentHashMap<>();
    }

    public static void main(String[] args) {

        LogRateLimiter logger = new LogRateLimiter();

        assert logger.shouldPrintMessage(1, "foo");
        assert logger.shouldPrintMessage(2, "bar");
        assert !logger.shouldPrintMessage(3, "foo");
        assert !logger.shouldPrintMessage(8, "bar");
        assert !logger.shouldPrintMessage(10, "foo");
        assert logger.shouldPrintMessage(11, "foo");
    }

    public boolean shouldPrintMessage(long seconds, String message) {
        // empty string,
        // negative seconds
        // seconds limit
        // cache memory limit

        if (wasCachedDuring(seconds, message)) {
            return false;
        } else {
            cache.put(message, seconds);
            return true;
        }

    }

    boolean wasCachedDuring(long seconds, String message) {
        if (!cache.containsKey(message)) {
            return false;
        }
        return seconds - cache.get(message) < SECONDS_INTERVAL;
    }
}