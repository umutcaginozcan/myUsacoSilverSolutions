package myUsacoSilverSolutions.PS_8_Recursion;

import java.util.*;

public class Subsets {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int N = in.nextInt();
        int[] set = new int[N];
        for (int i = 0; i < N; i++)
            set[i] = in.nextInt();

        ArrayList<Integer> currentSubset = new ArrayList<>();
        printSubsets(set, 0, currentSubset);
        in.close();
    }

    public static void printSubsets(int[] set, int start, ArrayList<Integer> currentSubset) {
        if (start == set.length) {
            System.out.println(currentSubset);
            return;
        }

        // Include the current element
        currentSubset.add(set[start]);
        printSubsets(set, start + 1, currentSubset);

        // Exclude the current element
        currentSubset.remove(currentSubset.size() - 1);
        printSubsets(set, start + 1, currentSubset);
    }
}