package myUsacoSilverSolutions.PS_7_NextGreaterElement;

import java.io.*;
import java.util.StringTokenizer;

public class usaco2009march {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] cows = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            cows[i] = Integer.parseInt(st.nextToken());
        }
        
        int[] results = new int[N];
        int max = 0, maxIndex = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (i == N - 1 || cows[i] >= max) {
                max = cows[i];
                maxIndex = i;
                results[i] = 0;
            } else 
                results[i] = maxIndex + 1;
        }

        for (Integer result: results) 
            System.out.println(result);
    }
}
