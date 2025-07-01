import java.util.LinkedList;

public class Day13_006 {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.add("hello");
        ll.add(1);
        ll.add('w');
        ll.add("w is a consonant");
        //System.out.println(ll.get(0));
        for(Object p : ll){
            System.out.println(p);
        }
        System.out.println("1st element = "+ ll.getFirst());
        System.out.println("last element = "+ ll.getLast());
        ll.remove(2);
        System.out.println("\n\nafter deleting");
        for(Object p : ll){
            System.out.println(p);
        }
        System.out.println("1st element = "+ ll.getFirst());
        System.out.println("last element = "+ ll.getLast());




    }
}
