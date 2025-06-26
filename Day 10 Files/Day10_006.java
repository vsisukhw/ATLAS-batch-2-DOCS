class Counter1 {
    private int count = 0;
    public synchronized void increment() {
        count++;
    }
    public int getCount() {
        return count;
    }
}
class ThreadDemo11 extends Thread {
    Counter1 counter;
    ThreadDemo11(Counter1 counter) {
        this.counter = counter;
    }
    public void run() {
        for (int i = 0; i < 10; i++) {
            counter.increment();
        }
    }
}

public class Day10_006 {
    public static void main(String[] args) {
        Counter1 counter = new Counter1();
        ThreadDemo11 t1 = new ThreadDemo11(counter);
        ThreadDemo11 t2 = new ThreadDemo11(counter);

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


