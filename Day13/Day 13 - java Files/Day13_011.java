import java.util.Arrays;
import java.util.LinkedList;

public class Day13_011 {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.add("hello");
        ll.add(1);
        ll.add('w');
        ll.add("w is a consonant");
        Object[] arr = ll.toArray();
//         System.out.println(Arrays.toString(arr));
        System.out.println(ll);


    }
}
