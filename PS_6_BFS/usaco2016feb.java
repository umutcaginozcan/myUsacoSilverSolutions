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

        Queue<Pair<Integer, Integer>> q = new LinkedList<>();
        boolean[][] visited = new boolean[X+1][Y+1]; 
        q.add(new Pair<>(0, 0));
        visited[0][0] = true;

        int bestDiff = Math.abs(M); 

        while (!q.isEmpty() && K-- > 0) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair<Integer, Integer> currentPair = q.poll();
                int curX = currentPair.first;
                int curY = currentPair.second;

                int[][] nextStates = new int[][] {
                    {X, curY}, {curX, Y}, {0, curY}, {curX, 0},
                    {Math.max(0, curX - (Y - curY)), Math.min(Y, curY + curX)},
                    {Math.min(X, curX + curY), Math.max(0, curY - (X - curX))}
                };

                for (int[] state : nextStates) {
                    if (!visited[state[0]][state[1]]) {
                        visited[state[0]][state[1]] = true;
                        q.add(new Pair<>(state[0], state[1]));
                        int totalMilk = state[0] + state[1];
                        int diff = Math.abs(M - totalMilk);
                        if (diff < bestDiff) {
                            bestDiff = diff;  /
                        }
                    }
                }
            }
        }

        pw.println(bestDiff);
        pw.close();
        br.close();
    }

    static class Pair<X, Y> {
        public X first;
        public Y second;
        Pair(X first, Y second) {
            this.first = first;
            this.second = second;
        }
    }
}