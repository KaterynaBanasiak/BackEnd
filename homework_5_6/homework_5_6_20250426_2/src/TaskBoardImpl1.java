public class TaskBoardImpl1 implements TaskBoard{
    private String task;

    @Override
    public synchronized void setTask(String task) {
        while (this.task!=null){
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        this.task = task;
        this.notify();
    }

    @Override
    public synchronized String getTask() {
        while (task == null){
            try {
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        String res = task;
        task = null;
        this.notifyAll();
        return res;
    }
}
