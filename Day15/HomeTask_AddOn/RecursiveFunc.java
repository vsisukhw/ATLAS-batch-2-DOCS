public class RecursiveFunc {

    // 1. Factorial of a number
    public static int factorial(int n) {
        if (n == 0 || n == 1)
            return 1;
        return n * factorial(n - 1);
    }

    // 2. Fibonacci number at position n
    public static int fibonacci(int n) {
        if (n <= 1)
            return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // 3. Reverse a string using recursion
    public static String reverseString(String str) {
        if (str.isEmpty())
            return str;
        return reverseString(str.substring(1)) + str.charAt(0);
    }

    // 4. Recursive search in array
    public static boolean search(int[] arr, int target, int index) {
        if (index == arr.length)
            return false;
        if (arr[index] == target)
            return true;
        return search(arr, target, index + 1);
    }

    // 5. Count digits in a number
    public static int countDigits(int n) {
        if (n == 0)
            return 0;
        return 1 + countDigits(n / 10);
    }

    // 6. Sum of digits in a number
    public static int sumDigits(int n) {
        if (n == 0)
            return 0;
        return (n % 10) + sumDigits(n / 10);
    }

    // 7. Reverse null-terminated string (same logic)
    public static String reverseNullTerminated(String str) {
        return reverseString(str);
    }

    // 8. Decimal to binary conversion
    public static void decimalToBinary(int n) {
        if (n == 0)
            return;
        decimalToBinary(n / 2);
        System.out.print(n % 2);
    }

    // 9. Check palindrome string
    public static boolean isPalindrome(String str, int start, int end) {
        if (start >= end)
            return true;
        if (str.charAt(start) != str.charAt(end))
            return false;
        return isPalindrome(str, start + 1, end - 1);
    }

    // 10. Copy array recursively
    public static void copyArray(int[] src, int[] dest, int index) {
        if (index == src.length)
            return;
        dest[index] = src[index];
        copyArray(src, dest, index + 1);
    }

    // === Main method ===
    public static void main(String[] args) {

        // Test 1: Factorial
        int num = 5;
        System.out.println("Factorial of " + num + ": " + factorial(num));

        // Test 2: Fibonacci series
        System.out.print("Fibonacci series (first 7 terms): ");
        for (int i = 0; i < 7; i++) {
            System.out.print(fibonacci(i) + " ");
        }
        System.out.println();

        // Test 3: Reverse string
        String originalStr = "hello";
        System.out.println("Original string: " + originalStr);
        System.out.println("Reversed string: " + reverseString(originalStr));

        // Test 4: Search in array
        int[] arr = {5, 10, 15, 20, 25};
        int target = 15;
        System.out.println("Searching for " + target + ": " + search(arr, target, 0));

        // Test 5 & 6: Count and Sum of digits
        int digitNum = 12345;
        System.out.println("Number: " + digitNum);
        System.out.println("Digit count: " + countDigits(digitNum));
        System.out.println("Sum of digits: " + sumDigits(digitNum));

        // Test 7: Reverse null-terminated string
        String nullTermStr = "world";
        System.out.println("Reverse of null-terminated string: " + reverseNullTerminated(nullTermStr));

        // Test 8: Decimal to Binary
        int decimal = 13;
        System.out.print("Binary of " + decimal + ": ");
        if (decimal == 0) System.out.print(0);
        else decimalToBinary(decimal);
        System.out.println();

        // Test 9: Palindrome check
        String palinStr = "madam";
        System.out.println("Is \"" + palinStr + "\" a palindrome? " + isPalindrome(palinStr, 0, palinStr.length() - 1));

        // Test 10: Copy array
        int[] source = {1, 2, 3, 4, 5};
        int[] destination = new int[source.length];
        copyArray(source, destination, 0);
        System.out.print("Copied array: ");
        for (int val : destination) {
            System.out.print(val + " ");
        }
    }
}
