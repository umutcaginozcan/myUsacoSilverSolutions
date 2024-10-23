package myUsacoSilverSolutions.PS_9_FloodFill;

import java.util.Scanner;

public class TheCastle {

    // Global variables here:
    static int[] roomCount = {0}, largest = {0};
    static int[][] grid, visited;
    static int r, c;
    static int[] rowD = {-1, 0, 1, 0}, colD = {0, 1, 0, -1};
    static int[] area = {0};

    public static void go1(int r1, int c1) {
        if (visited[r1][c1] == 0) {
            area[0]++;
            visited[r1][c1] = 1;
        } 

        for (int i = 0; i < 4; i++) {
            int r2 = rowD[i] + r1;
            int c2 = colD[i] + c1;

            if (r2 >= 0 && r2 < r && c2 >= 0 && c2 < c) {
                // Don't need any base condition because of here:
                if (i == 0 && visited[r2][c2] != 1 && (grid[r1][c1] == 1 || grid[r1][c1] == 4 || grid[r1][c1] == 8 ||
                    grid[r1][c1] == 5 || grid[r1][c1] == 9 || grid[r1][c1] == 12 || grid[r1][c1] == 13)) {
                        go1(r2, c2);
                    }
                if (i == 1 && visited[r2][c2] != 1 && (grid[r1][c1] == 1 || grid[r1][c1] == 2 || grid[r1][c1] == 8 ||
                    grid[r1][c1] == 3 || grid[r1][c1] == 9 || grid[r1][c1] == 10 || grid[r1][c1] == 11)) {
                        go1(r2, c2);
                    }
                if (i == 2 && visited[r2][c2] != 1 && (grid[r1][c1] == 1 || grid[r1][c1] == 2 || grid[r1][c1] == 4 ||
                    grid[r1][c1] == 3 || grid[r1][c1] == 5 || grid[r1][c1] == 6 || grid[r1][c1] == 7)) {
                        go1(r2, c2);
                    }    
                if (i == 3 && visited[r2][c2] != 1 && (grid[r1][c1] == 2 || grid[r1][c1] == 4 || grid[r1][c1] == 8 ||
                    grid[r1][c1] == 6 || grid[r1][c1] == 10 || grid[r1][c1] == 12 || grid[r1][c1] == 14)) {
                        go1(r2, c2);
                    }  
            }
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        r = in.nextInt();
        c = in.nextInt();

        grid = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                grid[i][j] = in.nextInt();
            }
        } 

        // For each box, call flood fill algorithm.
        visited = new int[r][c];
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (visited[i][j] == 0) {
                    go1(i, j);
                    roomCount[0]++;
                    largest[0] = Math.max(largest[0], area[0]);
                    area[0] = 0;
                }
            }
        } 

        go1(0, 0);
        System.out.println("Number of Rooms Is: " + roomCount[0]);
        System.out.println("Largest Area Is: " + largest[0]);
    }    
}