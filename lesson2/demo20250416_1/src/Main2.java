public class Main2 {
    public static void main(String[] args) {
        MyThread thread1 = new MyThread();
        Thread thread2 = new Thread(new MyRunnable());
        System.out.println("---- start main ");

        thread1.start();
        //thread1.join();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // любой код main
        //
        System.out.println("---- finish main ");



    }
}

