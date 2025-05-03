public class Manager implements Runnable{
    private  TaskBoard taskBoard;
    private  int nTask;

    public Manager(TaskBoard taskBoard, int nTask) {
        this.taskBoard = taskBoard;
        this.nTask = nTask;
    }

    @Override
    public void run() {
        for (int i = 0; i < nTask; i++) {
            taskBoard.setTask("task "+i);
            /*try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }*/
        }
    }
}
