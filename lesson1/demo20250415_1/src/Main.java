public class Main {
    public static void main(String[] args) {
        System.out.println("start main()");

        MyThread thread1 = new MyThread("Jack");
        MyThread thread2 = new MyThread("Nick");

        thread1.start();  /// not run() !!!
        thread2.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("Main: " + i);
        }

        System.out.println("-------- finish main");
    }
}
