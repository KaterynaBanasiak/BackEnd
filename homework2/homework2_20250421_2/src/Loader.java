import java.util.concurrent.ThreadLocalRandom;

public class Loader implements Runnable {

    private String name;
    private int nBox;
    private int done = 0;

    private Warehouse warehouse1;
    private Warehouse warehouse2;

    public Loader(String name, int nBox, Warehouse warehouse1, Warehouse warehouse2) {
        this.name = name;
        this.nBox = nBox;
        this.warehouse1 = warehouse1;
        this.warehouse2 = warehouse2;
    }

    @Override
    public void run() {
        while (done < nBox) {
            // за одну итерацию грузчик переносит 2 коробки (по одной на склад)
            if (done + 2 <= nBox) {
                warehouse1.addValue(1);
                warehouse2.addValue(1);
                done += 2;
            } else if (done + 1 == nBox) {
                // если осталась одна коробка (нечётное число), пусть отнесёт только на первый склад
                warehouse1.addValue(1);
                done += 1;
            }

            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(5, 16));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        System.out.println(name + " finished. Got: " + done + " boxes");

        // любой склад может регистрировать победителя, используем первый
        warehouse1.registerWinner(name);
    }
}

