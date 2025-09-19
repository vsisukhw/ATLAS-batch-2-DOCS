class Counter11 {
    private int count = 0;
    public void increment() {
        synchronized (this) {
            count++;
        }
    }
    public int getCount() {
        return count;
    }
}
class ThreadDemo111 extends Thread {
    Counter11 counter;
    ThreadDemo111(Counter11 counter) {
        this.counter = counter;
    }
    public void run() {
        for (int i = 0; i < 10; i++) {
            counter.increment();
        }
    }
}
public class Day10_007 {
    public static void main(String[] args) {
        Counter11 counter = new Counter11();
        ThreadDemo111 t1 = new ThreadDemo111(counter);
        ThreadDemo111 t2 = new ThreadDemo111(counter);

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


