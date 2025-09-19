import java.util.*;

public class Day11_018 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Accept 5 integers from user
        List<Integer> numbers = new ArrayList<>();
        System.out.println("Enter 20 integers:");
        for (int i = 0; i < 20; i++) {
            numbers.add(sc.nextInt());
        }
        numbers.stream()
                .limit(10)
                .forEach(System.out::println);
    }
}
