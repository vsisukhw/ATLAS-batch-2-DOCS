import java.util.ArrayList;
import java.util.List;

public class Day11_014new {
    public static void main(String[] args) {
        List<String> fullName = new ArrayList();
        fullName.add("Sukhwinder,Singh");
        fullName.add("ram,Singh");
        fullName.add("ram,verma");
        fullName.add("Ram,Ambani");
        fullName.add("Vijya,malya");
        fullName.add("anil,ambani");
        fullName.add("ram,oberoy");
        System.out.println("names starting with ram  :  ");

        fullName.stream()
                .filter((p)->p.startsWith("ram") || p.startsWith("Ram"))
                .map((p)->p.toUpperCase())
                .sorted()
                .forEach((p)-> System.out.println(p));

//        friends.stream()
//                .filter(name -> name.startsWith("ram"))       // Filter names starting with "Alice"
//                  .map(String::toUpperCase)                      // Convert to uppercase
//                  .sorted()                                      // Sort alphabetically
//                  .forEach(System.out::println);

    }
}
