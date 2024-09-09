// package myUsacoSilverSolutions.PS_5_DataStructures;

import java.util.*;
import java.io.*;

public class usaco2017feb {
    static class Cow implements Comparable<Cow> {
        int end;
        int start;

        Cow(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
		public int compareTo(Cow c) {
			return start != c.start ? start - c.start : end - c.end;
		}
    }

    public static void main(String[] args) throws IOException {
        BufferedReader fin = new BufferedReader(new FileReader("helpcross.in"));
        PrintWriter fout = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));

        StringTokenizer st = new StringTokenizer(fin.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        TreeSet<Integer> chickens = new TreeSet<>();
        List<Cow> cows = new ArrayList<>();

        for (int i = 0; i < C; i++) {
            chickens.add(Integer.parseInt(fin.readLine()));
        }
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(fin.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            cows.add(new Cow(start, end));
        }

        // Sorting cows based on their end times primarily
        Collections.sort(cows);

        int total = 0;
        for (Cow cow : cows) {
            // Finding the least chicken time that can help this cow
            Integer availableChicken = chickens.ceiling(cow.start);
            if (availableChicken != null && availableChicken <= cow.end) {
                total++;
                chickens.remove(availableChicken);
            }
        }

        fout.println(total);
        fout.close();
    }
}


