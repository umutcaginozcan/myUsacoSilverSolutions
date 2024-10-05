// package myUsacoSilverSolutions.PS_8_Recursion;

import java.io.*;
import java.util.*;

public class usaco2013marchP3 {
    static int[] cows;
    static int N;
    static int K;
    static int res;
    static char[][] R;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("assign.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("assign.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // no of cows
        K = Integer.parseInt(st.nextToken()); // no of relations
        R = new char[N + 1][N + 1];
        
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            char ch = st.nextToken().charAt(0);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            R[a][b] = ch;
            R[b][a] = ch;
        }

        cows = new int[N + 1];
        go(1);

        pw.println(res);
        pw.close();
    }

    public static void go(int start) {
        if (start > N) {
            res++;
            return;
        }
    
        for (int i = 1; i <= 3; i++) {
            cows[start] = i;
            if (isValidSoFar(start)) {  // Early check for validity up to the current index
                go(start + 1);
            }
        }
    }

    public static boolean isValidSoFar(int end) {
        for (int i = 1; i < end; i++) {
            if (R[i][end] == 'S' && cows[i] != cows [end])
                return false;
            if (R[i][end] == 'D' && cows[i] == cows [end])
                return false;
        }
        return true;
    }
}
