public class ThreadTest {
    public static void main(String[] args) {
        MyThread myThread = new MyThread(1);
        myThread.start();
        for (int i = 0;i<10;i++){
            System.out.println("aaa"+i);
        }
    }
}
