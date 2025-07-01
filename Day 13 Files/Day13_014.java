//Splititerator

import java.util.*;
class SplitIteraror01 {
    public static void main(String[] args)     {


        LinkedList<String> l = new LinkedList<>();


        l.add("Meher");
        l.add("Prasunamba");
        l.add(".MK");

        System.out.println(l);

        Spliterator<String> it = l.spliterator();


        System.out.println("Splitting the list:");
        it.forEachRemaining(System.out::println);
    }
}


//import java.util.*;
//
//public class Day13_014 {
//    public static void main(String[] args) {
//        LinkedList lobj = new LinkedList();
//        lobj.add("Prasunamba");
//        lobj.add("Meher");
//        lobj.add(".MK");
//        Spliterator<String> sitobj = lobj.spliterator();
//        //forEachRemaining is a method of Spliterator
//        System.out.println("Splitting the list:");
//        sitobj.forEachRemaining(System.out::println);
//    }
//}


//import java.util.*;
//
//public class Day13_014 {
//    public static void main(String[] args) {
//        List<String> lobj = new ArrayList<>();
//        lobj.add("Prasunamba");
//        lobj.add("Meher");
//        lobj.add(".MK");
//        lobj.add("Sukhwinder");
//        lobj.add("Ravi");
//
//        Spliterator<String> sit1 = lobj.spliterator();
//        Spliterator<String> sit2 = sit1.trySplit();
//
//        System.out.println("First half (sit2):");
//        if (sit2 != null) {
//            sit2.forEachRemaining(System.out::println);
//        } else {
//            System.out.println("Could not split.");
//        }
//
//        System.out.println("\nSecond half (sit1):");
//        sit1.forEachRemaining(System.out::println);
//    }
//}
//
