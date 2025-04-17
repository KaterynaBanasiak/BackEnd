public class Main {
    public static void main(String[] args) throws InterruptedException {
        String name = Thread.currentThread().getName();
        long id = Thread.currentThread().getId();
        System.out.println(name+" "+ id + " START");


        // создание Thread через анонимный класс
        Thread thread1 = new Thread(){    // Main$01 extends Thread
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+ " START анонимный класс");
            }
        };

        Thread[] threads = {
                new MyThread(),
                new MyThread(),
                new Thread(new MyRunnable()),
                new Thread(()-> { System.out.println(Thread.currentThread().getName()+ " START LAMBDA"); } ),
                thread1
        };


        for (Thread thread:threads){
            thread.start();
        }
        threads[threads.length-1].join();
        /*
        for (Thread thread:threads){
            thread.join();
        }

         */

        Thread.sleep(1000);
        System.out.println(name+" "+ id + " FINISH");

    }
}
