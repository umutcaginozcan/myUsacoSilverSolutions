package myUsacoSilverSolutions.Sorting;

import java.util.Arrays;

public class SortingIndices {
    public static void main(String[] args) {
        final int N = 5;
        Integer[] heights = { 5, 2, 3, 4, 1 };
        Integer[] widths = { 4, 2, 5, 6, 1 };
        String[] colors = { "blue", "white", "red", "black", "yellow"};
        Integer[] indices = new Integer[N];
        for (int i = 0; i < N; i++) {
            indices[i] = i;
        }
    
        Arrays.sort(indices, (i1, i2) -> (colors[i1].compareTo(colors[i2])));
        
        System.out.println( "After sorted by color:");
        for (int i = 0; i < N; i++) {
            int j = indices[i];
            System.out.println(colors[j] + " " + heights[j] + " " + widths[j]);
        }
        
        
        Arrays.sort(indices, (i1, i2) -> heights[i1] * widths[i1] - heights[i2] * widths[i2]);

        System.out.println( "After sorted by area:");
        for (int i = 0; i < N; i++) {
            int j = indices[i];
            System.out.println(colors[j] + " " + heights[j] + " " + widths[j]);
        }
    }
}