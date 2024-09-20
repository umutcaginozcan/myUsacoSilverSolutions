package myUsacoSilverSolutions.PS_8_Recursion;

import java.util.Scanner;

public class Hanoi {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(); // Number of Disks
        TowerOfHanoi(N, 1, 2, 3);
    }

    public static void TowerOfHanoi(int N, int cur, int target, int other) {
        if (N == 0) 
            return; 
        TowerOfHanoi(N - 1, cur, other, target);

        System.out.println("Move disk " + N + " from peg " + cur + " to peg " + target);

        TowerOfHanoi(N - 1, other, target, cur);
    }
}
