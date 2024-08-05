package Synchronous;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(3);

        for (int i = 1; i <= 3; i++) {
            new Thread(new Worker(latch, "Worker-" + i)).start();
        }

        try {

            latch.await();
            System.out.println("All workers have finished their tasks. Main thread proceeding.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Worker implements Runnable {
    private CountDownLatch latch;
    private String name;

    public Worker(CountDownLatch latch, String name) {
        this.latch = latch;
        this.name = name;
    }

    @Override
    public void run() {
        try {

            System.out.println(name + " is working...");
            Thread.sleep((int) (Math.random() * 1000));
            System.out.println(name + " has finished work.");

            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

