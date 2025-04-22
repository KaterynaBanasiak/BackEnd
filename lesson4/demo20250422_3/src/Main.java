public class Main {
    public static  Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Account accountA = new Account("DE1111","Jack", 1000);
        Account accountB = new Account("DE2222","John", 1000);

        Thread thread1 = new Thread(() -> transfer(accountA, accountB, 100), "T1");
        Thread thread2 = new Thread(() -> transfer(accountB, accountA, 200), "T2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(accountA);
        System.out.println(accountB);

    }

    public static synchronized void transfer(Account from, Account to, double amount){

        from.withdraw(amount);
        to.deposit(amount);


    }

}

