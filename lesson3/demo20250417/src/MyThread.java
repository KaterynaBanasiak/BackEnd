class MyThread extends Thread {

    @Override
    public void run() {
        while (!isInterrupted()) { // Проверяем флаг прерывания
            System.out.println("Поток работает..." + isInterrupted());

            try {
                Thread.sleep(1000); // Поток засыпает
            } catch (InterruptedException e) {
                System.out.println("Поток прерван во время  сна!");
                break; // Выход из цикла
            }


        }
        //System.out.println("Поток завершен.");
    }
}
