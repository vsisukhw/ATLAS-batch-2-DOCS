import java.util.LinkedList;

public class Day13_008 {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();

        ll.add("hello");
        ll.add(1);
        ll.add(22);
        ll.add('w');
        ll.add("w is a consonant");

        System.out.println(ll);

        ll.set(0,"welcome");

        System.out.println("\nafter updating\n");

        System.out.println(ll);




    }
}