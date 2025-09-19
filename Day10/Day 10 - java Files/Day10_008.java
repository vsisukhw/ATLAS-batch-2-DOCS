class Counter111 {
    private static int count = 0;
    public static synchronized void increment() {
            count++;
    }
    public static int getCount() {
        return count;
    }
}
class ThreadDemo1111 extends Thread {
    Counter111 counter;
    ThreadDemo1111(Counter111 counter) {
        this.counter = counter;
    }
    public void run() {
        for (int i = 0; i < 10; i++) {
            counter.increment();
        }
    }
}
public class Day10_008 {
    public static void main(String[] args) {
        Counter111 counter = new Counter111();
        ThreadDemo1111 t1 = new ThreadDemo1111(counter);
        ThreadDemo1111 t2 = new ThreadDemo1111(counter);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final count: " + counter.getCount());
    }
}


