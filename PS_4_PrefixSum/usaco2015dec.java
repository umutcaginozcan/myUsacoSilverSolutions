// package myUsacoSilverSolutions.PS_4_PrefixSum;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class usaco2015dec {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("bcount.in")); 
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());

        int[] cows = new int[N];
        for (int i = 0; i < N; i++) {
            cows[i] = Integer.parseInt(br.readLine());
        }

        int[] prefix_1 = new int[N + 1];
        int[] prefix_2 = new int[N + 1];
        int[] prefix_3 = new int[N + 1];
        for (int i = 0; i < N; i++) {
            if (cows[i] == 1) {
                prefix_1[i + 1] = prefix_1[i] + 1;
                prefix_2[i + 1] = prefix_2[i];
                prefix_3[i + 1] = prefix_3[i];
            } else if (cows[i] == 2) {
                prefix_2[i + 1] = prefix_2[i] + 1;
                prefix_1[i + 1] = prefix_1[i];
                prefix_3[i + 1] = prefix_3[i];
            } else if (cows[i] == 3) {
                prefix_3[i + 1] = prefix_3[i] + 1;
                prefix_1[i + 1] = prefix_1[i];
                prefix_2[i + 1] = prefix_2[i];
            }
        }

        while (Q > 0) {
            Q--;
            st = new StringTokenizer(br.readLine());
            int lower = Integer.parseInt(st.nextToken());
            int upper = Integer.parseInt(st.nextToken());
            pw.println((prefix_1[upper] - prefix_1[lower - 1]) + " " + (prefix_2[upper] - prefix_2[lower - 1]) + " " +
                (prefix_3[upper] - prefix_3[lower - 1]));
        }

        pw.flush();
        pw.close();
        br.close();
    }
}