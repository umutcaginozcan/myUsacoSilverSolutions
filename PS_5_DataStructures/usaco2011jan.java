package myUsacoSilverSolutions.PS_5_DataStructures;

import java.util.Scanner;
import java.util.Stack;

public class usaco2011jan {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        Stack<Integer> unwashed_undried = new Stack<>();
        for (int i = N; i >= 1; i--) {
            unwashed_undried.push(i);
        }
        Stack<Integer> washed_undried = new Stack<>();
        Stack<Integer> cleaned = new Stack<>();

        while (cleaned.size() < N) {
            int bessie_canmuu = in.nextInt();
            int count = in.nextInt();

            if (bessie_canmuu == 1) {
                while (count > 0 && !unwashed_undried.isEmpty()) {
                    washed_undried.push(unwashed_undried.pop());
                    count--;
                }
            } else {
                while (count > 0 && !washed_undried.isEmpty()) {
                    cleaned.push(washed_undried.pop());
                    count--;
                }
            }
        }

        while (!cleaned.isEmpty()) {
            System.out.println(cleaned.pop());
        }
        in.close();
    }
}
