import java.util.*;
import java.util.stream.Collectors;

public class Day11_016 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Accept 5 integers from user
        List<Integer> numbers = new ArrayList<>();
        System.out.println("Enter 5 integers:");
        for (int i = 0; i < 5; i++) {
            numbers.add(sc.nextInt());
        }
        numbers.stream()
                .filter(p -> p % 2 != 0 )
                .forEach(System.out::println);
    }
}
