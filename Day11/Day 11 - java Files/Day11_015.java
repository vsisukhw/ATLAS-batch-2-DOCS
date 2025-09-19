import java.util.ArrayList;
import java.util.stream.Collectors;

public class Day11_015 {
    public static void main(String[] args) {
        ArrayList<Integer> nos = new ArrayList<>();
        nos.add(2);
        nos.add(3);
        nos.add(4);
        nos.add(5);
        nos.add(6);



                nos.stream()
                        .map((p)->p*p)
                        //.collect(Collectors.toList());
                        .forEach(System.out::println);

        System.out.println(nos);


    }
}
