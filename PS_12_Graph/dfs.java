package myUsacoSilverSolutions.PS_12_Graph;

import java.util.Scanner;

public class dfs {
    final static int MAXN = 100;
    static int N;
    static int[][] adjMat = new int[MAXN + 1][MAXN + 1];
    static boolean[] visited = new boolean[MAXN + 1];

    public static void dfs(int u) {
        System.out.println(u);
        visited[u] = true;

        for (int v = 1; v <= N; v++) {
            if (!visited[v] && adjMat[u][v] == 1) 
                dfs(v);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        int M = in.nextInt();

        while (M-- > 0) {
            int u = in.nextInt();
            int v = in.nextInt();
            adjMat[u][v] = 1;
            adjMat[v][u] = 1;
        }

        dfs(1);
    }
}