import java.util.LinkedList;

public class Day13_013 {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.push("hello");
        ll.push(1);
        ll.push('w');
        ll.push("w is a consonant");
        System.out.println(ll);
        ll.pop();
        System.out.println(ll);
    }
}