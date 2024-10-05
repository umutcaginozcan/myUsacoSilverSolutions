package myUsacoSilverSolutions.PS_9_FloodFill;

import java.util.Scanner;

public class dfsImplementation {
    static int N, M, cnt;
    static int[][] grid;
    static int[] rowD = {-1, 0, 1, 0};
    static int[] colD = {0, 1, 0, -1};

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int M = in.nextInt();
        grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                grid[i][j] = in.nextInt();
            }
        }
        
        cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (grid[i][j] == 1) {
                    cnt++;
                    floodFill(i, j);
                }
            }
        }
        
        System.out.println(cnt);
    }

    public static void floodFill(int i, int j) {
        grid[i][j] = 0;
    
        for (int x = 0; x < 4; x++) {
            int ii = i + rowD[x];
            int jj = j + colD[x];
    
            if (ii < 0 || ii >= N || jj < 0 || jj >= M || grid[ii][jj] == 0) 
                continue;
            floodFill(ii, jj);
        }
    }
}
