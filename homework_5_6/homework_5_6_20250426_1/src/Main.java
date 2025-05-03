import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Account accountA = new Account("DE1111", "Jack", 1000);
        Account accountB = new Account("DE2222", "John", 1000);

        Thread thread1 = new Thread(() -> transfer(accountA, accountB, 100), "T1");
        Thread thread2 = new Thread(() -> transfer(accountB, accountA, 500), "T2");
        Thread lockMonitor = new DeadlockDetector();

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(accountA);
        System.out.println(accountB);
    }

    public static void transfer(Account from, Account to, double amount) {
        while (true) {
            try {
                if (from.getLock().tryLock(1, TimeUnit.SECONDS)) {
                    try {
                        System.out.println("Locked from account by " + Thread.currentThread().getName());
                        if (to.getLock().tryLock(1, TimeUnit.SECONDS)) {
                            try {
                                System.out.println("Locked to account by " + Thread.currentThread().getName());
                                if (from.withdraw(amount)) {
                                    to.deposit(amount);
                                    System.out.println("Transfer successful by " + Thread.currentThread().getName());
                                } else {
                                    System.out.println("Insufficient funds for " + Thread.currentThread().getName());
                                }
                                return; // Завершаем после успешного перевода
                            } finally {
                                to.getLock().unlock();
                                System.out.println("Unlocked to account by " + Thread.currentThread().getName());
                            }
                        }
                    } finally {
                        from.getLock().unlock();
                        System.out.println("Unlocked from account by " + Thread.currentThread().getName());
                    }
                }
                // Подождать перед повторной попыткой захвата блокировки
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
