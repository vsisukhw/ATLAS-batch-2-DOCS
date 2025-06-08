class Calculation1 {
    int z;
    public void addition(int x, int y) {
        z = x + y;
        System.out.println("The sum of the given numbers:"+z);
    }
    public void Subtraction(int x, int y) {
        z = x - y;
        System.out.println("The difference between the given numbers:"+z);
    }
}
class Clock{
    void dis(){
        System.out.println("hello");
    }
}
public class Task032 extends Calculation1{
    public void multiplication(int x, int y) {
        z = x * y;
        System.out.println("The product of the given numbers:"+z);
    }
    public static void main(String args[]) {
        int a = 20, b = 10;
        Task031 demo = new Task031();
        demo.addition(a, b);
        demo.Subtraction(a, b);
        demo.multiplication(a, b);
    }
}

