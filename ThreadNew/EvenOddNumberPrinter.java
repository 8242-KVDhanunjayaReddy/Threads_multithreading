package ThreadNew;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class EvenOddNumberPrinter {
    private final int maxNum;
    private int currentNum = 1;
    private final ReentrantLock lock = new ReentrantLock();
    private final Condition evenCondition = lock.newCondition();
    private final Condition oddCondition = lock.newCondition();

    public EvenOddNumberPrinter(int maxNum) {
        this.maxNum = maxNum;
    }

    public void printEven() {
        while (currentNum <= maxNum) {
            lock.lock();
            try {
                while (currentNum % 2 != 0) {
                    evenCondition.await();
                }
                if (currentNum <= maxNum) {
                    System.out.println("Even: " + currentNum);
                    currentNum++;
                    oddCondition.signal();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printOdd() {
        while (currentNum <= maxNum) {
            lock.lock();
            try {
                while (currentNum % 2 == 0) {
                    oddCondition.await();
                }
                if (currentNum <= maxNum) {
                    System.out.println("Odd: " + currentNum);
                    currentNum++;
                    evenCondition.signal();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }

    public void startPrinting() {
        Thread evenThread = new Thread(this::printEven);
        Thread oddThread = new Thread(this::printOdd);
        evenThread.start();
        oddThread.start();
        try {
            evenThread.join();
            oddThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        EvenOddNumberPrinter printer = new EvenOddNumberPrinter(10);
        printer.startPrinting();
    }
}
