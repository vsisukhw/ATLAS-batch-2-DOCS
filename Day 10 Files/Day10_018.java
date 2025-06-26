class Test extends Thread{
    public void run1(){
        System.out.println("thread started.");
    }
}

public class Day10_018 {
    public static void main(String args[]){

        Test t1 = new Test();
        t1.run1();

    }
}
