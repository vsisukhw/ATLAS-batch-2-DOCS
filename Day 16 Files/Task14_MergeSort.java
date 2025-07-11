public class Task14_MergeSort {

    // Main function to sort an array using merge sort
    void sort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Recursively sort first and second halves
            sort(arr, left, mid);
            sort(arr, mid + 1, right);

            // Merge the sorted halves
            merge(arr, left, mid, right);
        }
    }

    // Merges two subarrays of arr[]
    void merge(int[] arr, int left, int mid, int right) {
        // Sizes of two subarrays
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Temp arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; i++)
            L[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[mid + 1 + j];

        // Merge the temp arrays
        int i = 0, j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else {
                arr[k++] = R[j++];
            }
        }

        // Copy remaining elements of L[]
        while (i < n1) {
            arr[k++] = L[i++];
        }

        // Copy remaining elements of R[]
        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    // Main method to test the code
    public static void main(String[] args) {
        int[] arr = {5, 2, 4, 6, 1, 3};
        int n = arr.length;

        Task14_MergeSort ms = new Task14_MergeSort();
        ms.sort(arr, 0, n - 1);

        // Print the sorted array
        for (int a : arr) {
            System.out.print(a + " ");
        }
    }
}
