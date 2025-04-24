public class Main {
    public static void main(String[] args) throws InterruptedException {
        TaskBoard taskBoard = new TaskBoardImpl1();

        Thread manager = new Thread(new Manager(taskBoard));
        Thread worker1 = new Thread(new Worker(taskBoard, "jack-1"));
        Thread worker2 = new Thread(new Worker(taskBoard, "jack-2"));

        worker1.setDaemon(true);
        worker2.setDaemon(true);
        worker1.start();
        worker2.start();
        //Thread.sleep(1);

        manager.start();
        manager.join();

    }
}
