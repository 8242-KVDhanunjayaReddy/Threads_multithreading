package MultiThreading;

public class Singleton {
    private Singleton() {}

    private static class SingletonHelper {
        private static final Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHelper.instance;
    }

    public static void main(String[] args) {

        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        System.out.println("Are both instances the same? " + (instance1 == instance2));
    }
}


