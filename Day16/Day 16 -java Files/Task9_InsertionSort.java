public class Task9_InsertionSort {

    void insertionSort(int[] a, int n) {
        for (int i = 1; i < n; i++) {
            int next = a[i];  // Store the current element
            int j;

            // Shift all elements greater than 'next' to the right
            for (j = i - 1; j >= 0 && a[j] > next; j--) {
                a[j + 1] = a[j];
            }

            // Insert 'next' at the correct sorted position
            a[j + 1] = next;
        }

        // Print the sorted array
        for (int num : a) {
            System.out.print(num + " ");
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 2, 4, 6, 1, 3};
        int n = arr.length;

        Task9_InsertionSort obj = new Task9_InsertionSort();
        obj.insertionSort(arr, n);
    }
}
