import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class DeadlockDetector extends Thread {

    private final ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();

    public DeadlockDetector() {
        this.setDaemon(true);
        this.start();

    }

    @Override
    public void run() {
        while (true) {
            long[] deadlockedThreadIds = threadMXBean.findDeadlockedThreads();

            if (deadlockedThreadIds != null && deadlockedThreadIds.length > 0) {
                System.out.println("Deadlock detected!");

                ThreadInfo[] threadInfos = threadMXBean.getThreadInfo(deadlockedThreadIds);
                for (ThreadInfo threadInfo : threadInfos) {
                    System.out.println("Thread involved in deadlock: " + threadInfo.getThreadName());
                    System.out.println(threadInfo);
                }
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException ignored) {}
        }
    }
}

