import java.util.*;

public class Day14_007 {
    public static void main(String[] args) {
        Stack obj = new Stack();
        obj.push(1);
        obj.push(2);
        obj.push(3);
        obj.push('f');
        obj.push(5);
        obj.push("hello");

        System.out.println(obj);
        System.out.println(obj.peek());
    }
}
