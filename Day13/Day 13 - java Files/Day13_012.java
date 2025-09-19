import java.util.Arrays;
import java.util.LinkedList;

public class Day13_012 {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.add("hello");
        ll.add(1);
        ll.add('w');
        ll.add("w is a consonant");
        System.out.println(ll);

        LinkedList pp = (LinkedList) ll.clone();

        System.out.println(pp);
        System.out.println(ll);


    }
}