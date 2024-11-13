package myUsacoSilverSolutions.PS_12_Graph;

import java.util.LinkedList;
import java.util.Queue;

public class bfs {
    public static void main(String[] args) {
        int[][] graph = new int[8][8];
        graph[1][2]++;
        graph[2][1]++;
        graph[1][3]++;
        graph[3][1]++;
        graph[2][4]++;
        graph[4][2]++;
        graph[2][5]++;
        graph[5][2]++;
        graph[3][6]++;
        graph[6][3]++;
        graph[3][7]++;
        graph[7][3]++;

        Queue<Integer> bfs = new LinkedList<Integer>();
        boolean[] visited = new boolean[8];
        bfs.add(1);
        while (!bfs.isEmpty()) {
            for (int i = 1; i <= 7; i++) {
                if (graph[bfs.peek()][i] == 1 && !visited[i]) {
                    bfs.add(i);
                }
            }
            System.out.println(bfs.peek());
            visited[bfs.peek()] = true;
            bfs.poll();
        }
    }
}
