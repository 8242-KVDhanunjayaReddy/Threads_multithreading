package Threading;

public class ThreadInterruptionDemo {
    public static void main(String[] args) {
        Thread taskThread = new Thread(new TaskFile());
        taskThread.start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            System.out.println("Main_method.Main thread interrupted");
        }


        System.out.println("Interrupting the task thread...");
        taskThread.interrupt();
    }
}

class TaskFile implements Runnable {
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("Task is running...");
            try {
                Thread.sleep(500); // Simulate some work
            } catch (InterruptedException e) {
                System.out.println("Task thread interrupted during sleep");
                Thread.currentThread().interrupt(); // Preserve the interrupt status
            }
        }
        System.out.println("Task thread exiting...");
    }
}

