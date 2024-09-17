package myUsacoSilverSolutions.PS_6_BFS;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class usaco2007open {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int K = in.nextInt();

        boolean[] visited = new boolean[100001];
        int[] times = new int[100001];
        Arrays.fill(times, -1);
        visited[N] = true; 
        times[N] = 0; // our start location

        Queue<Integer> visitQueue = new LinkedList<>();
        visitQueue.add(N);

        while (!visitQueue.isEmpty()) {
            int current = visitQueue.poll();
            int[] neighbors = {current - 1, current + 1, 2 * current};

            for (int neighbor : neighbors) {
                if (neighbor >= 0 && neighbor < 100000 && !visited[neighbor]) {
                    visitQueue.add(neighbor);
                    visited[neighbor] = true;
                    times[neighbor] = times[current] + 1;
                    
                    if (neighbor == K) {
                        System.out.println(times[neighbor]);
                        return;
                    }
                }
            }
        }

        System.out.println(-1);
    }
}