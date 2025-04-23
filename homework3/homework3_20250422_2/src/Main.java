import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        LatencyMonitor monitor = new LatencyMonitor();
        int numberOfServers = 10;

        Thread[] threads = new Thread[numberOfServers];

        for (int i = 0; i < numberOfServers; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    long latency = ThreadLocalRandom.current().nextLong(0, Integer.MAX_VALUE);
                    monitor.updateLatency(latency);
                }
            }, "Server-" + i);
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        System.out.println("Minimal latency recorded: " + monitor.getMinLatency() + " ms");
    }
}
