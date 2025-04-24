public class Manager implements Runnable{
    private  TaskBoard taskBoard;
    private static final int N_TASKS=10;

    public Manager(TaskBoard taskBoard) {
        this.taskBoard = taskBoard;
    }

    @Override
    public void run() {
        for (int i = 0; i < N_TASKS ; i++) {
            taskBoard.setTask("task "+i);
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
