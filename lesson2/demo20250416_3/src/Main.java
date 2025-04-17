import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Timer timer = new Timer();
        Thread thread = new Thread(timer);

        thread.setDaemon(true);
        thread.start();

        while (true) {
            System.out.println("Input time-out or press q for stop");
            String str = scanner.nextLine();
            if (str.equals("q")) {
                break;
            } else {
                try {
                    timer.setTimeOut(Integer.parseInt(str));  // Integer.parseInt -  convert str -> int
                } catch (Exception e){
                    timer.setTimeOut(3);
                }
            }
        }
        System.out.println("Main finish");
    }
}
