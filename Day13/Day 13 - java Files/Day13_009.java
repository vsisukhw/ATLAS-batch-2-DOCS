import java.util.LinkedList;

public class Day13_009 {
    public static void main(String[] args) {
        LinkedList ll = new LinkedList();
        ll.add("hello");
        ll.add(1);
        ll.add('w');
        ll.add("w is a consonant");
        System.out.println("using for and get method");
        int size=ll.size();
        for(int i=0;i<size;i++){
            System.out.println(ll.get(i));
        }

        System.out.println("\n\nusing for each loop");
        for(Object p : ll){
            System.out.println(p);
        }





    }
}