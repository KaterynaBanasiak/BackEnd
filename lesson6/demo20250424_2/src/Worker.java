public class Worker implements Runnable{
    private TaskBoard taskBoard;
    private String name;

    public Worker(TaskBoard taskBoard, String name) {
        this.taskBoard = taskBoard;
        this.name = name;
    }

    @Override
    public void run() {
        while (true){
            String task = taskBoard.getTask();
            System.out.println(task + " " + name);
        }
    }
}
