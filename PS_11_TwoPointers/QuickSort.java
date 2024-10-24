package myUsacoSilverSolutions.PS_11_TwoPointers;

public class QuickSort {
    static int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 8, 9};

    public static void quicksort(int[] arr, int start, int end) {
        if (start >= end) return;

        // Partition Logic:
        // 1: We don't need to use extra memory unlike merge sort.
        // 2: To do this we will use two pointers.
        // One for elements smaller than the pivot (j), one for larger (i).

        // Start by choosing a pivot. (I'll use the rightmost element as pivot)
        int pivot = arr[end];
        
        int j = start - 1; // This pointer will help us find elements smaller than the pivot
        // The pointer i, the one for the large will move by default.
        for (int i = start; i < end; i++) {
            if (arr[i] < pivot) {
                j++;
                // Swap arr[j] and arr[i].
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap the pivot into the correct position
        int temp = arr[j + 1];
        arr[j + 1] = arr[end];
        arr[end] = temp;

        // Recursive call: Deal with the left and right part of our pivot.
        quicksort(arr, start, j); // Right boundary is `j` because pivot is at `j + 1`
        quicksort(arr, j + 2, end); // Left boundary is `j + 2` because pivot is at `j + 1`
    }

    public static void main(String[] args) {
        quicksort(arr, 0, arr.length - 1);
        for (int i: arr)
            System.out.print(i + " ");
    }
}
