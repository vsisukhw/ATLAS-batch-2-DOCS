import java.util.stream.*;
class Day10_012 {
    public static void main(String[] args) {
        Stream<String> stream
                = Stream.of("Hello", "My",
                "name", "is",
                "Sukhwinder",
                "Singh");

        stream.forEach(System.out::println);
    }
}

