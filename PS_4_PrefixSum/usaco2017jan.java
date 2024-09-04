// package myUsacoSilverSolutions.PS_4_PrefixSum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class usaco2017jan {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("hps.in")); 
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        String[] played = new String[N];
        for (int i = 0; i < N; i++) {
            played[i] = br.readLine();
        }

        int[] sum_H = new int[N + 1];
        int[] sum_P = new int[N + 1];
        int[] sum_S = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            sum_H[i] = sum_H[i - 1];
            sum_P[i] = sum_P[i - 1];
            sum_S[i] = sum_S[i - 1];

            if (played[i - 1].equals("H")) sum_H[i]++;
            else if (played[i - 1].equals("P")) sum_P[i]++;
            if (played[i - 1].equals("S")) sum_S[i]++;
        }

        int res = 0;
        for (int i = 1; i <= N; i++) {
            int cur = 0;
            int lower_sumH = sum_H[i] - sum_H[0]; 
            int lower_sumP = sum_P[i] - sum_P[0]; 
            int lower_sumS = sum_S[i] - sum_S[0]; 

            int higher_sumH = sum_H[N] - sum_H[i];
            int higher_sumP = sum_P[N] - sum_P[i];
            int higher_sumS = sum_S[N] - sum_S[i];

            cur += Math.max(Math.max(lower_sumH, lower_sumP), lower_sumS) 
                + Math.max(Math.max(higher_sumH, higher_sumP), higher_sumS);
            res = Math.max(res, cur);
        }
        
        pw.println(res);
        pw.close();
    }
}