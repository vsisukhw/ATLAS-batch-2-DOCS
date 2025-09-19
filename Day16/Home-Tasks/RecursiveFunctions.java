public class RecursiveFunctions {

    // 1. Recursive function to search an element in an array
    public static boolean searchElement(int[] arr, int index, int target) {
        if (index >= arr.length) return false;
        if (arr[index] == target) return true;
        return searchElement(arr, index + 1, target);
    }

    // 2a. Recursive function to count the digits of a positive integer
    public static int countDigits(int n) {
        if (n == 0) return 0;
        return 1 + countDigits(n / 10);
    }

    // 2b. Recursive function to find the sum of digits of a positive integer
    public static int sumDigits(int n) {
        if (n == 0) return 0;
        return n % 10 + sumDigits(n / 10);
    }

    // 3. Recursive function to reverse a null-terminated string (C-style string)
    public static String reverseString(String str) {
        if (str == null || str.length() <= 1) return str;
        return reverseString(str.substring(1)) + str.charAt(0);
    }

    // 4. Recursive function to convert decimal to binary
    public static String decimalToBinary(int n) {
        if (n == 0) return "0";
        if (n == 1) return "1";
        return decimalToBinary(n / 2) + (n % 2);
    }

    // Main method to test all functions
    public static void main(String[] args) {
        int[] array = {3, 7, 1, 9, 5};
        int searchFor = 9;
        System.out.println("Element " + searchFor + " found: " + searchElement(array, 0, searchFor));

        int number = 12345;
        System.out.println("Count of digits in " + number + ": " + countDigits(number));
        System.out.println("Sum of digits in " + number + ": " + sumDigits(number));

        String original = "hello";
        String reversed = reverseString(original);
        System.out.println("Original string: " + original);
        System.out.println("Reversed string: " + reversed);

        int decimal = 19;
        System.out.println("Binary of " + decimal + ": " + decimalToBinary(decimal));
    }
}
