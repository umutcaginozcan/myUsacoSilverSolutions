// package myUsacoSilverSolutions.PS_5_DataStructures;

import java.util.HashSet;
import java.util.Scanner;

/* 
https://cses.fi/problemset/task/1621/ 
*/
public class DistinctNumbers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        HashSet<Integer> myFirstHashSet = new HashSet<>();
        for (int i = 0; i < N; i++) {
            myFirstHashSet.add(in.nextInt());
        }
        System.out.println(myFirstHashSet.size());
    }
}
