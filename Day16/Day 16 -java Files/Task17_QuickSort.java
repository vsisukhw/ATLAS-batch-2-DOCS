
public class Task17_QuickSort {

    // Quick Sort function
    void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);

            // Recursively sort left and right partitions
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    // Partition function
    int partition(int[] arr, int low, int high) {
        int pivot = arr[high];  // Choosing the last element as pivot
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                // Swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Place pivot in correct position
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1;
    }

    // Main method
    public static void main(String[] args) {
        int[] arr = {9, 3, 7, 5, 1, 4, 6, 8, 2};
        int n = arr.length;

        Task17_QuickSort sorter = new Task17_QuickSort();
        sorter.quickSort(arr, 0, n - 1);

        // Print sorted array
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
