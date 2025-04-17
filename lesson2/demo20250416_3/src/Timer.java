import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Timer implements Runnable {
    private  int timeOut = 2;

    public void setTimeOut(int timeOut) {
        this.timeOut = timeOut;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm:ss")));
            //int qwr = Math.min(10, 11);// Ctrl+Alt+V    /// Ctrl+alt+C

            try {
                Thread.sleep(timeOut *1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
