// package myUsacoSilverSolutions.PS_3_Strings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class usaco2014feb {
    public static void main(String[] args) throws IOException {
        // BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // PrintWriter pw = new PrintWriter(System.out);
        BufferedReader br = new BufferedReader(new FileReader("auto.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("auto.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int W = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        String[] dictionary = new String[W];
        for (int i = 0; i < W; i++) {
            dictionary[i] = br.readLine();
        }

        String[] sorted_dictionary = Arrays.copyOf(dictionary, W);
        Arrays.sort(sorted_dictionary);

        while (N > 0) {
            N--;
            st = new StringTokenizer(br.readLine());
            int ith_word = Integer.parseInt(st.nextToken());
            String prefix = st.nextToken();

            ArrayList<String> word_pool = new ArrayList<>();
            int startIndex = Arrays.binarySearch(sorted_dictionary, prefix);
            if (startIndex < 0) {
                startIndex = Math.abs(startIndex + 1);
            }

            for (int i = startIndex; i < W; i++) {
                if (sorted_dictionary[i].startsWith(prefix)) {
                    word_pool.add(sorted_dictionary[i]);
                } else {
                    break;
                }
            }

            if (ith_word > word_pool.size()) {
                pw.println(-1);
            } else {
                String found_word = word_pool.get(ith_word - 1);
                for (int i = 0; i < dictionary.length; i++) {
                    if (found_word.equals(dictionary[i])) {
                        pw.println(i + 1);
                        break;
                    }
                }
            }
        }
        
        pw.flush();
        pw.close();
        br.close();
    }
}