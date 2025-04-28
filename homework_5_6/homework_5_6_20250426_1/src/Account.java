import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private final String iban;
    private final String owner;
    private double balance;
    private final Lock lock = new ReentrantLock(); // Новый Lock для каждого аккаунта

    public Account(String iban, String owner, double balance) {
        this.iban = iban;
        this.owner = owner;
        this.balance = balance;
    }

    public boolean deposit(double amount) {
        balance += amount;
        return true;
    }

    public boolean withdraw(double amount) {
        if (getBalance() >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public double getBalance() {
        return balance;
    }

    public Lock getLock() {
        return lock;
    }

    @Override
    public String toString() {
        return "Account{" +
                "iban='" + iban + '\'' +
                ", owner='" + owner + '\'' +
                ", balance=" + balance +
                '}';
    }
}
