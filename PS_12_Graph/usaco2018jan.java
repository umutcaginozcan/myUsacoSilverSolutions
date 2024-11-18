// package myUsacoSilverSolutions.PS_12_Graph;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class usaco2018jan {

    static int cnt, k;
    static ArrayList<ArrayList<int[]>> adjList;

    public static void dfs(int u, int p) {
        cnt++;

        for (int[] e: adjList.get(u)) {
            int v = e[0];
            int w = e[1];
            if (v != p && w >= k) {
                dfs(v, u);
            }
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader("mootube.in"));
        PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken()); 
        
        adjList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjList.add(new ArrayList<int[]>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList.get(u).add(new int[] {v, w});
            adjList.get(v).add(new int[] {u, w});
        }

        while (Q > 0) {
            Q--;
            st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            int u = Integer.parseInt(st.nextToken());
            cnt = 0;
            dfs(u, u);
            pw.println(cnt - 1);
        }
        pw.close();
    }
}