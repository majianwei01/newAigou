public class TestSyn implements Runnable {
    public static final Object lockHelper = new Object();
    static int count = 0;
    public void increase() { for (int i = 0; i < 10000; i++) { count++; } }
    @Override public void run() {
        increase();
    }
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new TestSyn());
        Thread t2 = new Thread(new TestSyn());
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(count); }
}
