// package myUsacoSilverSolutions.PS_5_DataStructures;

import java.util.*;
import java.io.*;

public class usaco2017feb {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("helpcross.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        TreeMap<Integer, Integer> ms = new TreeMap<>(); // multiset!
        
        for (int i = 0; i < C; i++) {
            int x = Integer.parseInt(br.readLine());
            if (ms.containsKey(x)) 
                ms.put(x, ms.get(x) + 1);
            else 
                ms.put(x, 1);
        }
        
        ArrayList<ArrayList<Integer>> cows = new ArrayList<ArrayList<Integer> >();
        for (int i = 0; i < N; i++) {
            cows.add(new ArrayList<Integer>());

            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            cows.get(i).add(x1);
            cows.get(i).add(x2);
        }

        Collections.sort(cows, (a,  b) -> {
            if (a.get(1) == b.get(1))
                return a.get(0) - b.get(0);
            return a.get(1) - b.get(1);
        });
        
        int count = 0;
        for (ArrayList<Integer> cow: cows) {
            int x1 = cow.get(0);
            int x2 = cow.get(1);

            Integer x = ms.ceilingKey(x1);
            if (x != null && x <= x2) {
                count++;
                if (ms.get(x) == 1) 
                    ms.remove(x);
                else
                    ms.put(x, ms.get(x) - 1);
            }
        }

        pw.println(count);
        pw.close();
    }
}