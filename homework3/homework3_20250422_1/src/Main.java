public class Main {

    public static void main(String[] args) throws InterruptedException {
        Account accountA = new Account("DE1111", "Jack", 1000);
        Account accountB = new Account("DE2222", "John", 1000);

        Thread thread1 = new Thread(() -> transfer(accountA, accountB, 100), "T1");
        Thread thread2 = new Thread(() -> transfer(accountB, accountA, 200), "T2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Final account states:");
        System.out.println(accountA);
        System.out.println(accountB);
    }

    public static void transfer(Account from, Account to, double amount) {
        Account firstLock = from;
        Account secondLock = to;

        // Упорядочиваем локи по хэш-коду, чтобы избежать deadlock
        if (System.identityHashCode(from) > System.identityHashCode(to)) {
            firstLock = to;
            secondLock = from;
        }

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                    System.out.println(Thread.currentThread().getName() + ": Transferred " + amount + " from " + from + " to " + to);
                } else {
                    System.out.println(Thread.currentThread().getName() + ": Not enough balance to transfer");
                }
            }
        }
    }
}
