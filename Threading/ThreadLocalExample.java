package Threading;

public class ThreadLocalExample {

    private static ThreadLocal<Integer> threadLocalVariable = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {

        Thread thread1 = new Thread(new Task(), "Thread-1");
        Thread thread2 = new Thread(new Task(), "Thread-2");
        Thread thread3 = new Thread(new Task(), "Thread-3");

        thread1.start();
        thread2.start();
        thread3.start();
    }

    static class Task implements Runnable {
        @Override
        public void run() {

            String threadName = Thread.currentThread().getName();

            Integer initialValue = threadLocalVariable.get();
            System.out.println(threadName + " initial value: " + initialValue);


            threadLocalVariable.set((int) (Math.random() * 100));


            Integer newValue = threadLocalVariable.get();
            System.out.println(threadName + " new value: " + newValue);
        }
    }
}

