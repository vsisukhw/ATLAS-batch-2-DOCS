import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
class Counter1111 {
    private int count = 0;
    private final Lock lock = new ReentrantLock();
    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }
    public int getCount() {
        return count;
    }
}
class ThreadDemo11111 extends Thread {
    Counter1111 counter;
    ThreadDemo11111(Counter1111 counter) {
        this.counter = counter;
    }
    public void run() {
        for (int i = 0; i < 10; i++) {
            counter.increment();
        }
    }
}
public class Day10_009 {
    public static void main(String[] args) {
        Counter1111 counter = new Counter1111();
        ThreadDemo11111 t1 = new ThreadDemo11111(counter);
        ThreadDemo11111 t2 = new ThreadDemo11111(counter);
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


