// package myUsacoSilverSolutions.PS_1_Sorting;

import java.util.*;
import java.io.*;

public class usaco2020jan {
    static int N, K;
    static Integer[] A;
    static int mod;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("berries.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("berries.out")));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new Integer[N];
        
        st = new StringTokenizer(br.readLine());
        int M = 0;
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            M = Math.max(M, A[i]);
        }
        
        int best = 0;
        for (int b = 1; b <= M; b++) {
            int full = 0;
            for (int i = 0; i < N; i++) {
                full += A[i] / b;
            }
            if (full < K / 2) {
                break;
            }
            if (full >= K) {
                best = Math.max(best, b * (K / 2));
                continue;
            }
            
            mod = b;
            Arrays.sort(A, new Comparator<Integer>() {
                public int compare(Integer a, Integer b) {
                    return Integer.compare(b % mod, a % mod);
                }
            });

            int cur = b * (full - K / 2);
            for (int i = 0; i < N && i + full < K; i++) {
                cur += A[i] % b;
            }
            best = Math.max(best, cur);
        }
        
        pw.println(best);
        pw.close();
    }
}