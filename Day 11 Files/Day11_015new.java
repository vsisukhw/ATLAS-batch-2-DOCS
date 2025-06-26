package streams;

import java.util.*;
import java.util.stream.Collectors;

public class Day11_015new {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Accept 5 integers from user
        List<Integer> numbers = new ArrayList<>();
        System.out.println("Enter 5 integers:");
        for (int i = 0; i < 5; i++) {
            numbers.add(sc.nextInt());
        }

        List<Integer> squares = numbers.stream()
                .map(n -> n * n)
                .collect(Collectors.toList());

        System.out.println("Squares of the numbers:");
        squares.forEach(System.out::println);

        sc.close();
    }
}