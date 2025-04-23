import java.util.concurrent.atomic.AtomicLong;

public class LatencyMonitor {
    private final AtomicLong minLatency;

    public LatencyMonitor() {
        // Инициализируем максимальным возможным значением
        this.minLatency = new AtomicLong(Long.MAX_VALUE);
    }

    public void updateLatency(long latency) {
        minLatency.updateAndGet(current -> Math.min(current, latency));
    }

    public long getMinLatency() {
        return minLatency.get();
    }
}

