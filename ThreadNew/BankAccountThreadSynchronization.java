package ThreadNew;

class BankAccount {
    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public synchronized void withdraw(int amount) {
        if (balance >= amount) {
            System.out.println(Thread.currentThread().getName() + " is withdrawing " + amount);
            balance -= amount;
            System.out.println(Thread.currentThread().getName() + " completed withdrawal. Remaining balance: " + balance);
        } else {
            System.out.println(Thread.currentThread().getName() + " attempted to withdraw " + amount + " but insufficient balance.");
        }
    }

    public int getBalance() {
        return balance;
    }
}

class AccountHolder extends Thread {
    private BankAccount account;
    private int amount;

    public AccountHolder(BankAccount account, int amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void run() {
        account.withdraw(amount);
    }
}

public class BankAccountThreadSynchronization {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        AccountHolder holder1 = new AccountHolder(account, 500);
        AccountHolder holder2 = new AccountHolder(account, 700);
        AccountHolder holder3 = new AccountHolder(account, 300);

        holder1.setName("Holder 1");
        holder2.setName("Holder 2");
        holder3.setName("Holder 3");

        holder1.start();
        holder2.start();
        holder3.start();
    }
}

