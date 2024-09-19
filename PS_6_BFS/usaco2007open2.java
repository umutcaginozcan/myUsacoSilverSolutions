import java.io.*;
import java.util.*;

public class usaco2007open2 {
    static class Pair {
        int first;
        int second;
        Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char[][] grid = new char[R][C];
        for (int i = 0; i < R; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        int[][] bfs = new int[R][C];
        int cowr = 0, cowc = 0, barnr = 0, barnc = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (grid[i][j] == 'B') {
                    barnr = i;
                    barnc = j;
                }
                if (grid[i][j] == 'C') {
                    cowr = i;
                    cowc = j;
                }
            }
        }

        Queue<Pair> visitNeighbours = new LinkedList<>();
        visitNeighbours.add(new Pair(cowr, cowc));
        bfs[cowr][cowc] = 1;

        while (!visitNeighbours.isEmpty()) {
            Pair current = visitNeighbours.poll();
            int currentr = current.first;
            int currentc = current.second;

            int[][] neighbors = {{currentr - 1, currentc}, {currentr, currentc - 1},
                {currentr + 1, currentc}, {currentr, currentc + 1}};

            for (int[] neighbor : neighbors) {
                if (neighbor[0] < 0 || neighbor[0] >= R || neighbor[1] < 0 || neighbor[1] >= C || 
                    grid[neighbor[0]][neighbor[1]] == '*' || bfs[neighbor[0]][neighbor[1]] != 0) continue;
                bfs[neighbor[0]][neighbor[1]] = bfs[currentr][currentc] + 1;
                visitNeighbours.add(new Pair(neighbor[0], neighbor[1]));
            }
        }

        pw.println(bfs[barnr][barnc] - 1); // Subtract one to not count the ending cell
        pw.close();
    }
}
