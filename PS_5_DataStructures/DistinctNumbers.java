// package myUsacoSilverSolutions.PS_5_DataStructures;

import java.util.Scanner;

/* 
https://cses.fi/problemset/task/1621/ 
*/
public class DistinctNumbers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int count = 0;
        int[] numbers = new int[N];
        outerloop:
        for (int i = 0; i < N; i++) {
            numbers[i] = in.nextInt();
            if (i == 0) {
                count++;
                continue;
            }

            for (int j = i - 1; j >= 0; j--) {
                if (numbers[i] == numbers[j])
                    continue outerloop;
            }
            count++;
        }
        System.out.println(count);
    }
}
