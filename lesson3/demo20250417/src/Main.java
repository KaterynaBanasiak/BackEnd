public class Main {
    public static void main(String[] args) throws InterruptedException  {
        MyThread thread = new MyThread();
        thread.start();
        Thread.sleep(3000);
        thread .interrupt(); // Отправляем запрос на прерывание
    }
}
