import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskBoardImpl2 implements TaskBoard {
    private String task;

    private Lock lock = new ReentrantLock();
    private Condition managerCondition = lock.newCondition();
    private Condition workerCondition = lock.newCondition();


    @Override
    public void setTask(String task) {
        lock.lock();
        try {
            while (this.task != null) {
                try {
                    managerCondition.await();         // !!!! не wait()
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            this.task = task;
            workerCondition.signal();   // !!!!  не notify()

        } finally {
            lock.unlock();
        }
    }

    @Override
    public String getTask() {
        lock.lock();
        try {
            while (task == null) {
                try {
                    workerCondition.await();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            String result = task;
            task = null;
            managerCondition.signal();
            return result;

        } finally {
            lock.unlock();
        }

    }
}

