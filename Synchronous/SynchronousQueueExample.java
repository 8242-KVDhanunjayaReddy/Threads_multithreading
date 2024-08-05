package Synchronous;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SynchronousQueueExample {
    public static void main(String[] args) {

        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);


        Thread producer = new Thread(() -> {
            try {
                int value = 42;
                System.out.println("Producing value: " + value);
                queue.put(value); // This will block if the queue is full
                System.out.println("Value produced: " + value);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });


        Thread consumer = new Thread(() -> {
            try {
                System.out.println("Waiting to consume...");
                int value = queue.take();
                System.out.println("Consumed value: " + value);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
    }
}

