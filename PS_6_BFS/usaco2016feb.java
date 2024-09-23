import java.io.*;
import java.util.*;

public class usaco2016feb {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("pails.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());  
        int Y = Integer.parseInt(st.nextToken());  
        int K = Integer.parseInt(st.nextToken());  
        int M = Integer.parseInt(st.nextToken());  

        Queue<Integer> qX = new LinkedList<>();
        Queue<Integer> qY = new LinkedList<>();
        int[][] step = new int[X+1][Y+1]; 
        Arrays.fill(step, -1);
        // Initial state
        step[0][0] = 0;
        qX.add(0);
        qY.add(0);

        while (!qX.isEmpty()) {
            int x = qX.poll();
            int y = qY.poll();

            if (step[x][y] >= K)
                continue; // Continue with the next element in the queue

            // Transitions
            // 1) Empty X
            int xx = 0;
            int yy = y;
            if (step[xx][yy] < 0) {
                step[xx][yy] = step[x][y] + 1;
                qX.add(xx);
                qY.add(yy);
            }

            // 2) Empty Y
            
            // 3) Full X
            xx = X;
            yy = y;
            if (step[xx][yy] < 0) {
                step[xx][yy] = step[x][y] + 1;
                qX.add(xx);
                qY.add(yy);
            }

            // 4) Full Y

            // 5) X -> Y
            int val = Math.min(x, Y - y);
            xx = x - val;
            yy = y + val;
            if (step[xx][yy] < 0) {
                step[xx][yy] = step[x][y] + 1;
                qX.add(xx);
                qY.add(yy);
            }

            // 6) Y -> X
        }

        int best = M;
        for (int i = 0; i <= X; i++) {
            for (int j = 0; j <= Y; j++) {
                if (step[i][j] >= 0) {
                    int cur = i + j;
                    best = Math.min(best, Math.abs(M - cur));
                }
            }
        }

        pw.println(best);
        pw.close();
        br.close();
    }
}