package myUsacoSilverSolutions.Sorting;

import java.util.Arrays;

public class sorting {
    public static void main(String[] args) {
    final int N = 10;
    Integer numbers[] = {8, -5, 0, 1, 11, 15, -16, -9, 2, -4};

    Arrays.sort(numbers, (a, b) -> Math.abs(a) - Math.abs(b));

    for (Integer num: numbers) System.out.print(num + " ");
    }
}
