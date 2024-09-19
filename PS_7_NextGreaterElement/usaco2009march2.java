package myUsacoSilverSolutions.PS_7_NextGreaterElement;

import java.io.*;
import java.util.*;

public class usaco2009march2 {
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        
        int N = in.nextInt();
        int[] cows = new int[N];
        for (int i = 0; i < N; i++) {
            cows[i] = in.nextInt();
        }

        int[] result = new int[N];
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < N; i++) {
            while (!stack.isEmpty() && cows[stack.peek()] < cows[i]) {
                int index = stack.pop();
                result[index] = i + 1; // Save the index +1 for 1-based index
            }
            stack.push(i);
        }
        
        // Cows with no greater cow to the right
        while (!stack.isEmpty()) {
            result[stack.pop()] = 0;
        }

        // Output results
        for (int res : result) {
            System.out.println(res);
        }
    }
}

/* 
3
2
6
1
1
2 


2
3

 */