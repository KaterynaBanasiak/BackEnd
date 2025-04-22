public class Warehouse {
    private String title;
    private int value;
    private Object lock = new Object();
    private volatile String firstFinishedName = null;

    public Warehouse(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Warehouse{" +
                "title='" + title + '\'' +
                ", value=" + value +
                ", firstFinishedName='" + firstFinishedName + '\'' +
                '}';
    }

    public void addValue(int value) {
        synchronized (lock) {
            this.value += value;
        }
    }

    public void registerWinner(String name) {
        synchronized (lock) {
            if (firstFinishedName == null) {
                firstFinishedName = name;
                System.out.println("🎉 " + name + " finished first and gets a bonus!");
            }
        }
    }
}
