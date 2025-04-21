import java.util.concurrent.ThreadLocalRandom;

public class Loader implements Runnable {

    private String name;
    private int nBox;
    private int capacity;
    private Warehouse warehouse;
    private int done = 0;

    public Loader(String name, int nBox, int capacity, Warehouse warehouse) {
        this.name = name;
        this.nBox = nBox;
        this.capacity = capacity;
        this.warehouse = warehouse;
    }

    @Override
    public void run() {
        while (done < nBox) {
            int value = Math.min(nBox - done, capacity);
            warehouse.addValue(value);
            done += capacity;

            // Пауза между итерациями
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(5, 16));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println(name + " finished. Got: " + done + " boxes");

        // Регистрация победителя
        warehouse.registerWinner(name);
    }
}

