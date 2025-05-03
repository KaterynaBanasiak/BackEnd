import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskBoardImpl2 implements TaskBoard {
    private final Queue<String> tasks = new LinkedList<>();
    private final int MAX_TASKS = 10;

    private final Lock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    @Override
    public void setTask(String task) {
        lock.lock();
        try {
            while (tasks.size() >= MAX_TASKS) {
                notFull.await();
            }
            tasks.add(task);
            notEmpty.signal();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String getTask() {
        lock.lock();
        try {
            while (tasks.isEmpty()) {
                notEmpty.await();
            }
            String task = tasks.poll();
            notFull.signal();
            return task;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            lock.unlock();
        }
    }
}
