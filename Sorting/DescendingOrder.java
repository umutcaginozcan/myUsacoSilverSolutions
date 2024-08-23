package myUsacoSilverSolutions.Sorting;

import java.util.Arrays;
import java.util.Collections;

public class DescendingOrder {
    public static void main(String[] args) {
        Integer[] numbers = {4, -5, 0, 1234, 5, 15, 543, -987, 5, 4};

        // Sorting the array in descending order
        Arrays.sort(numbers, Collections.reverseOrder());

        // Printing the sorted array
        System.out.println(Arrays.toString(numbers));
    }
}
