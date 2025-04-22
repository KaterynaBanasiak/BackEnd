public class MyThread extends Thread{
    private volatile boolean isFinish = false;
    private int counter=0;

    public boolean isFinish() {
        return isFinish;
    }

    public  void setFinish(boolean finish) {
        isFinish = finish;
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public void run() {
        System.out.println("Thread start");
        while (!isFinish){
            counter++;
        }
        System.out.println("Thread finish " + counter);
    }
}

