import java.io.*;
import java.util.*;

public class usaco2007open2 {
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
        
        Queue<Integer> qx = new LinkedList<>();
        Queue<Integer> qy = new LinkedList<>();
        int[][] dp = new int[R][C];
        // Initial State
        qx.add(cowr);
        qy.add(cowc);
        dp[cowr][cowc] = 1;

        int[] xdif = {-1, 0, 1, 0};
        int[] ydif = {0, 1, 0, -1};

        while (!qx.isEmpty()) {
            int x = qx.poll();
            int y = qy.poll();

            for (int i = 0; i < 4; i++) {
                int xx = x + xdif[i];
                int yy = y + ydif[i];

                if (xx < 0 || xx >= R || yy < 0 || yy >= C) 
                    continue;

                if (grid[xx][yy] != '*' && dp[xx][yy] == 0) {
                    dp[xx][yy] = dp[x][y] + 1;
                    qx.add(xx);
                    qy.add(yy);
                }
            }
        }
        
        // Printing the path
        ArrayList<int[]> XY = new ArrayList<>();
        int x = barnr;
        int y = barnc;
        int K = dp[barnr][barnc];
        while (K > 1) {
            XY.add(new int[]{x, y});
            System.out.println(K);

            for (int i = 0; i <= 3; i++) {
                int xx = x + xdif[i];
                int yy = y + ydif[i];

                if (xx < 0 || xx >= R || yy < 0 || yy >= C)
                    continue;
                
                if (dp[xx][yy] == K - 1) {
                    x = xx;
                    y = yy;
                    K--;
                    break;
                }
            }
        }
        
        XY.add(new int[]{x, y});
        
        System.out.println(dp[barnr][barnc] - 1);
        Collections.reverse(XY);
        for (int[] arr: XY) {
            System.out.println(arr[0] + " " + arr[1]);
        }
        pw.close();
    }
}
