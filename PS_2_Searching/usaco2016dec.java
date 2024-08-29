// package myUsacoSilverSolutions.PS_2_Searching;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class usaco2016dec {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(new File("haybales.in")));
		PrintWriter out = new PrintWriter("haybales.out");
		StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int[] haybaleLocations = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            haybaleLocations[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(haybaleLocations);
        while (Q > 0) {
            Q--;
            st = new StringTokenizer(br.readLine());
            int loc1 = Integer.parseInt(st.nextToken());
            loc1 = Arrays.binarySearch(haybaleLocations, loc1);
            if (loc1 < 0) {
                loc1 = Math.abs(loc1 + 1); // Find the upper bound, insertion point.
            }
            int loc2 = Integer.parseInt(st.nextToken());
            loc2 = Arrays.binarySearch(haybaleLocations, loc2);
            if (loc2 < 0) {
                loc2 = Math.abs(loc2 + 2); // Find the upper bound, insertion point.
            }
            out.println(loc2 - loc1 + 1);
        }
        out.close();
    }
}
