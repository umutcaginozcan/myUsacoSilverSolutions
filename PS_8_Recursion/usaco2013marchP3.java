// package myUsacoSilverSolutions.PS_8_Recursion;

import java.io.*;
import java.util.*;

public class usaco2013marchP3 {
    static int[] cows;
    static int N;
    static int K;
    static String[] relations;
    static int[] c1;
    static int[] c2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("assign.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("assign.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // no of cows
        K = Integer.parseInt(st.nextToken()); // no of relations
        
        relations = new String[K];
        c1 = new int[K];
        c2 = new int[K];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            relations[i] = st.nextToken();
            c1[i] = Integer.parseInt(st.nextToken()) - 1; // Adjusting for 0-based index
            c2[i] = Integer.parseInt(st.nextToken()) - 1; // Adjusting for 0-based index
        }

        int res[] = {0};
        cows = new int[N];
        go(cows, 0, res);

        pw.println(res[0]);
        pw.close();
    }

    public static void go(int[] cows, int start, int[] res) {
        if (start >= cows.length) {
            if (isValid(cows))
                res[0]++;
            return;
        }
    
        for (int i = 1; i <= 3; i++) {
            cows[start] = i;
            if (isValidSoFar(cows, start)) {  // Early check for validity up to the current index
                go(cows, start + 1, res);
            }
        }
    }

    public static boolean isValid(int[] cows) {
        for (int i = 0; i < K; i++) {
            if ((relations[i].equals("S") && cows[c1[i]] != cows[c2[i]])
                || (relations[i].equals("D") && cows[c1[i]] == cows[c2[i]]))
                return false;
        }
        return true;
    }

    public static boolean isValidSoFar(int[] cows, int end) {
        for (int i = 0; i < K; i++) {
            if (c1[i] <= end && c2[i] <= end) {
                if ((relations[i].equals("S") && cows[c1[i]] != cows[c2[i]])
                    || (relations[i].equals("D") && cows[c1[i]] == cows[c2[i]]))
                    return false;
            }
        }
        return true;
    }
}
