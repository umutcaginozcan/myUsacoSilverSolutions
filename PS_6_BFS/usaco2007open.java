package myUsacoSilverSolutions.PS_6_BFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class usaco2007open {
    final static int X = 100000;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int K = in.nextInt();

        int[] times = new int[100001];
        Arrays.fill(times, -1);
        times[N] = 0; // our start location

        Queue<Integer> visitQueue = new LinkedList<>();
        visitQueue.add(N);

        while (!visitQueue.isEmpty()) {
            int current = visitQueue.poll();
            int[] neighbors = {current - 1, current + 1, 2 * current};

            for (int neighbor : neighbors) {
                if (neighbor >= 0 && neighbor <= X && times[neighbor] < 0) {
                    visitQueue.add(neighbor);
                    times[neighbor] = times[current] + 1;                    
                }
            }
        }

        System.out.println(times[K]);
    }
}