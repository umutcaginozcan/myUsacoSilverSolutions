package myUsacoSilverSolutions.PS_8_Recursion;

import java.util.Scanner;

public class BinaryNumbers {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        print(N, "");
        in.close();
    }

    public static void print(int N, String prefix) {
        if (N == 0) {
            System.out.println(prefix);
            return;
        }
        
        print(N - 1, prefix + "0");
        print(N - 1, prefix + "1");
    }
}
